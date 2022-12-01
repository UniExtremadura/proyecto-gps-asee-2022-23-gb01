package es.unex.asee_proyectoprueba.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import es.unex.asee_proyectoprueba.R;
import es.unex.asee_proyectoprueba.adapters.CommentAdapter;
import es.unex.asee_proyectoprueba.adapters.TabsViewPagerAdapter;
import es.unex.asee_proyectoprueba.model.Comments;
import es.unex.asee_proyectoprueba.model.Films;
import es.unex.asee_proyectoprueba.room.FilmsDatabase;
import es.unex.asee_proyectoprueba.sharedinterfaces.ItemDetailInterface;
import es.unex.asee_proyectoprueba.support.AppExecutors;

public class ItemDetailActivity extends AppCompatActivity implements ItemDetailInterface, CommentAdapter.DeleteCommentInterface  {

    // Objetos necesarios para la gestión de los Tabs
    TabLayout tabLayout;
    ViewPager2 viewPager2;

    // Lista de comentarios en la que se recuperan los comentarios de la película que ha sido seleccionada
    // Se busca evitar transacciones con la BD
    List<Comments> commentList = new ArrayList<>();

    // Objeto película con el que se recupera la información básica de la película seleccionada
    Films film;

    // Objeto preferencias para tener acceso al usuario que ha iniciado sesión
    SharedPreferences loginPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        setTitle(R.string.detail_title);

        tabLayout = findViewById(R.id.tlDetail);
        viewPager2 = findViewById(R.id.vpDetail);

        loginPreferences = getSharedPreferences(getPackageName()+"_preferences", Context.MODE_PRIVATE);

        viewPager2.setAdapter(new TabsViewPagerAdapter(this.getSupportFragmentManager(), getLifecycle()));
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Se obtiene la película de la que se quiere mostrar información
        film = (Films) getIntent().getSerializableExtra("FILM");

        // Se carga el objeto film con la información válida. Ya está disponible para compartir esta información con los fragmentos de Info y Social
        obtainFilmData();
    }

    /**
     * Carga el objeto Films film declarado como campo de la clase con la información básica que hay almacenada de la película.
     * Carga la lista de comentarios commentList declarada como campo de la clase con todos los comentarios que se han hecho sobre la película.
     */
    private void obtainFilmData() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                FilmsDatabase db = FilmsDatabase.getInstance(ItemDetailActivity.this);
                commentList = db.commentDAO().getFilmComments(film.getId());
                film = db.filmDAO().getFilm(film.getId());
            }
        });
    }

    /**
     * Devuelve los comentarios que se tiene de la película en cuestión.
     * @return Lista de comentarios con los comentarios actuales (vivos) de la película en cuestión
     */
    @Override
    public List<Comments> getCommentList() {
        return commentList;
    }

    /**
     * Elimina un comentario de una película. Como esta acción se registra en un botón que aparece en cada uno de los elementos de la
     * RecyclerView de los comentarios, se requiere actualizar dicho adapter. Por ello se pasa su referencia.
     * @param comment Comentario que se desea eliminar
     * @param commentAdapter Adapter que lanza el borrado del comentario
     */
    @Override
    public void deleteComment(Comments comment, CommentAdapter commentAdapter) {

    }

    /**
     * Devuelve la película seleccionada. Se emplea para la comunicación con los fragmentos que implementan las tabs.
     * @return Película que ha sido seleccionada y de la que se quiere mostrar la información de detalle
     */
    @Override
    public Films getFilmSelected() {
        return film;
    }

    /**
     * Al destruirse la actividad de detalle se actualizan los comentarios de la película en cuestión. Como durante la ejecución
     * de la aplicación solo se pueden modificar los comentarios del usuario loggeado, se actualizan eliminando los que estaban almacenados
     * antes del inicio de la actividad de detalle y se insertan según el contenido de la lista de comentarios que mantiene los datos vivos.
     * Se evita así realizar transacciones a la BD cada vez que se añade o elimina un comentario de una película.
     */
    @Override
    protected void onStop() {
        super.onStop();
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                FilmsDatabase db = FilmsDatabase.getInstance(ItemDetailActivity.this);
                db.commentDAO().deleteCommentsUserFilm(film.getId(),loginPreferences.getString("USERNAME", ""));
                db.commentDAO().insertAllComment(commentList);
            }
        });
    }
}