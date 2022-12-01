package es.unex.asee_proyectoprueba.API;

import es.unex.asee_proyectoprueba.model.FilmsPages;
import es.unex.asee_proyectoprueba.model.GenresList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmAPI {
    @GET("/3/movie/popular")
    Call<FilmsPages> getFilms(
            @Query("api_key") String api_key,
            @Query("language") String language
            //@Query("page") String page
    );

    @GET("/3/genre/movie/list")
    Call<GenresList> getGenres(
            @Query("api_key") String api_key,
            @Query("language") String language
    );
}
