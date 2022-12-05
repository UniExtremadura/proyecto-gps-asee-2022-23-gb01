package es.unex.asee_proyectoprueba.ui.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.unex.asee_proyectoprueba.support.AppExecutors;
import es.unex.asee_proyectoprueba.R;
import es.unex.asee_proyectoprueba.adapters.FilmAdapter;
import es.unex.asee_proyectoprueba.model.Films;
import es.unex.asee_proyectoprueba.model.Genre;
import es.unex.asee_proyectoprueba.room.FilmsDatabase;
import es.unex.asee_proyectoprueba.support.LevenshteinSearch;

public class ExploreFragment extends Fragment {

    private SearchView svSearchFilm;
    private ImageButton ibResetFilms;
    private ChipGroup cgGenreFilter;

    private List<Films> filmList = new ArrayList<>();
    private FilmAdapter filmAdapter;

    public ExploreFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        getViewReferences(view);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_explore);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        filmAdapter = new FilmAdapter(filmList, R.layout.explore_item_grid_content, getContext());
        recyclerView.setAdapter(filmAdapter);

        loadExploreFilms();
        loadChipFilters();


        svSearchFilm.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Films> queryResult = new ArrayList<>();
                for (Films film : filmList) {
                    if (LevenshteinSearch.distance(film.getTitle().toLowerCase(), s.toLowerCase()) <= 3 || film.getTitle().toLowerCase().contains(s.toLowerCase())) {
                        queryResult.add(film);
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        filmAdapter.swap(queryResult);
                    }
                });
                return false;
            }

        });

        ibResetFilms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filmAdapter.swap(filmList);
            }
        });

        return view;
    }

    private void getViewReferences(View view) {
        svSearchFilm = view.findViewById(R.id.svSearchFilm);
        ibResetFilms = view.findViewById(R.id.ibResetFilms);
        cgGenreFilter = view.findViewById(R.id.cgGenreFilter);
    }

    /**
     * Carga las películas obtenidas de la API y que han sido almacenadas en la BD
     */
    private void loadExploreFilms() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                FilmsDatabase db = FilmsDatabase.getInstance(getContext());
                filmList = db.filmDAO().getAllFilms();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        filmAdapter.swap(filmList);
                    }
                });
            }
        });
    }


    private void loadChipFilters() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                FilmsDatabase db = FilmsDatabase.getInstance(getContext());
                List<Genre> genres = db.genreDAO().getAllGenres();
                Collections.sort(genres);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (Genre genre : genres) {
                            Chip chip = new Chip(getContext());
                            chip.setText(genre.getName());
                            chip.setChipBackgroundColorResource(R.color.gray);
                            chip.setCloseIconVisible(false);
                            cgGenreFilter.addView(chip);
                            chip.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    loadGenreFilms(genre.getId());
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void loadGenreFilms(int genreID) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                FilmsDatabase db = FilmsDatabase.getInstance(getContext());
                List<Films> filmGenreList = db.filmDAO().getFilmsByGenres(genreID);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        filmAdapter.swap(filmGenreList);
                    }
                });
            }
        });
    }
}