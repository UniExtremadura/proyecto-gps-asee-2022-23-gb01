package es.unex.asee_proyectoprueba.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import es.unex.asee_proyectoprueba.R;
import es.unex.asee_proyectoprueba.model.Favorites;
import es.unex.asee_proyectoprueba.model.Films;
import es.unex.asee_proyectoprueba.model.Pendings;
import es.unex.asee_proyectoprueba.room.FilmsDatabase;
import es.unex.asee_proyectoprueba.sharedinterfaces.ItemDetailInterface;
import es.unex.asee_proyectoprueba.support.AppExecutors;
import es.unex.asee_proyectoprueba.support.UserFilmsData;

public class ItemDetailInfoFragment extends Fragment {

    // Referencias a vistas de la UI
    private ImageView ivMoviePosterDetail;
    private TextView tvMovieTitleDetail;
    private TextView tvReleaseDateValueDetail;
    private TextView tvRatingAPIDetail;
    private TextView tvRatingValueDetail;
    private TextView tvMovieGenresValue;
    private TextView tvSynopsisValueDetail;
    private Button bToggleFavoriteDetail;
    private Button bTogglePendingDetail;

    private List<String> listGenres = new ArrayList<>();

    // Interfaz para comunicarse con la actividad ItemDetailActivity y obtener de ella la información básica de la película
    private ItemDetailInterface itemDetailInterface;

    // Objeto película con el que se recupera la información básica de la película seleccionada
    Films film;

    // Objeto preferencias para tener acceso al usuario que ha iniciado sesión
    SharedPreferences loginPreferences;

    // Objeto para la sincronización de hilos necesario a la hora de recuperar y mostrar los géneros de la película
    public static final Object lock = new Object();

    public ItemDetailInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = new View(getContext());
        loginPreferences = getActivity().getSharedPreferences(getActivity().getPackageName()+"_preferences", Context.MODE_PRIVATE);

        // Se obtiene la película de la que se quiere mostrar información
        film = itemDetailInterface.getFilmSelected();

        return view;
    }

     /**
     * Consulta las listas que mantienen la información viva del usuario respecto a sus películas favoritas para determinar si está o no
     * marcada como tal.
     * @return True si la película está marcada como favorita o False en caso contrario
     */
    private boolean filmInFavorites(){
        UserFilmsData userFilmsData = UserFilmsData.getInstance();
        return userFilmsData.userFavoriteFilms.get(film.getId()) != null;
    }

    /**
     * Añade la película a la lista de favoritos del usuario. Para ello, actualiza la información viva del usuario respecto a las películas
     * favoritas y la añade también a la base de datos.
     */
    private void addFilmToFavorites() {
        UserFilmsData userFilmsData = UserFilmsData.getInstance();
        userFilmsData.userFavoriteFilms.put(film.getId(), film);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                FilmsDatabase db = FilmsDatabase.getInstance(getActivity());
                db.favoritesDAO().insertFavorites(new Favorites(film.getId(), loginPreferences.getString("USERNAME", "")));
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            itemDetailInterface = (ItemDetailInterface) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context + " must implement ItemDetailInterface");
        }
    }
}