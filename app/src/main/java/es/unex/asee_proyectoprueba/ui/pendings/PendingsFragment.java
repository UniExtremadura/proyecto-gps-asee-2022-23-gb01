package es.unex.asee_proyectoprueba.ui.pendings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.unex.asee_proyectoprueba.support.AppExecutors;
import es.unex.asee_proyectoprueba.R;
import es.unex.asee_proyectoprueba.support.UserFilmsData;
import es.unex.asee_proyectoprueba.adapters.FilmListAdapter;
import es.unex.asee_proyectoprueba.model.Films;
import es.unex.asee_proyectoprueba.room.FilmsDatabase;

public class PendingsFragment extends Fragment {

    private List<Films> filmList = new ArrayList<>();
    private FilmListAdapter filmListAdapter;
    private List<Integer>  filmsID = new ArrayList<>();
    private SharedPreferences preferences;

    public PendingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_pendings, container, false);
        filmListAdapter = new FilmListAdapter(filmList,R.layout.pendings_item_list_content, getContext());
        loadExploreFilms();
        View recyclerView = v.findViewById(R.id.fragment_pendings);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        return v;

    }

    // Se asigna a la lista los datos de las películas pendientes
    public void setupRecyclerView(@NonNull RecyclerView recyclerView){
        recyclerView.setAdapter(filmListAdapter);
    }

    private void loadExploreFilms() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                FilmsDatabase db = FilmsDatabase.getInstance(getContext());
                // Necesito meter como parametro el nombre de usuario aqui y no se como hacerlo
                preferences = getActivity().getSharedPreferences(getActivity().getPackageName()+"_preferences", Context.MODE_PRIVATE);
                filmsID = db.pendingsDAO().getFilmsIDPendings(preferences.getString("USERNAME",""));
                filmList = db.filmDAO().getFilmsByID(filmsID);
            }
        });
    }

    @Override
    public void onResume() {
        UserFilmsData userFilmsData = UserFilmsData.getInstance();
        List<Films> userPendingFilms  = new ArrayList<>(userFilmsData.userPendingFilms.values());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                filmListAdapter.swap(userPendingFilms);
            }
        });
        super.onResume();
    }
}