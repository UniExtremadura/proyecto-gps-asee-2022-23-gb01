package es.unex.asee_proyectoprueba;

import static junit.framework.TestCase.assertTrue;

import org.junit.Test;

import java.io.IOException;

import es.unex.asee_proyectoprueba.API.FilmAPI;
import es.unex.asee_proyectoprueba.model.FilmsPages;
import es.unex.asee_proyectoprueba.model.GenresList;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CU08_API_UnitTest {
    @Test
    public void shouldGetFilms() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MockResponse responde = new MockResponse()
                .addHeader("Content-Type", "application/json")
                        .setBody("{\n" +
                                "    \"page\": 1,\n" +
                                "    \"results\": [\n" +
                                "        {\n" +
                                "            \"adult\": false,\n" +
                                "            \"backdrop_path\": \"/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg\",\n" +
                                "            \"genre_ids\": [\n" +
                                "                28,\n" +
                                "                14,\n" +
                                "                878\n" +
                                "            ],\n" +
                                "            \"id\": 436270,\n" +
                                "            \"original_language\": \"en\",\n" +
                                "            \"original_title\": \"Black Adam\",\n" +
                                "            \"overview\": \"Casi 5.000 años después de haber sido dotado de los poderes omnipotentes de los antiguos dioses y encarcelado con la misma rapidez, Black Adam es liberado de su tumba terrenal, listo para desatar su forma única de justicia en el mundo moderno.\",\n" +
                                "            \"popularity\": 6579.615,\n" +
                                "            \"poster_path\": \"/mPTzXksC8HcAj6EM6WjZFJVJEzF.jpg\",\n" +
                                "            \"release_date\": \"2022-10-19\",\n" +
                                "            \"title\": \"Black Adam\",\n" +
                                "            \"video\": false,\n" +
                                "            \"vote_average\": 7.3,\n" +
                                "            \"vote_count\": 2508\n" +
                                "        }\n" +
                                "    ],\n" +
                                "    \"total_pages\": 36202,\n" +
                                "    \"total_results\": 724033\n" +
                                "}");
        mockWebServer.enqueue(responde);
        FilmAPI filmAPI = retrofit.create(FilmAPI.class);

        Call <FilmsPages>  call = filmAPI.getFilms("key","lang");
        Response <FilmsPages> respuesta = call.execute();

        assertTrue(respuesta != null);
        assertTrue(respuesta.isSuccessful());

        //Finish web server
        mockWebServer.shutdown();
    }

    @Test
    public void shouldGetGenres() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MockResponse responde = new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody("{\n" +
                        "    \"genres\": [\n" +
                        "        {\n" +
                        "            \"id\": 28,\n" +
                        "            \"name\": \"Acción\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 12,\n" +
                        "            \"name\": \"Aventura\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 16,\n" +
                        "            \"name\": \"Animación\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}");
        mockWebServer.enqueue(responde);
        FilmAPI filmAPI = retrofit.create(FilmAPI.class);

        Call <GenresList>  call = filmAPI.getGenres("key","lang");
        Response <GenresList> respuesta = call.execute();

        //let's check that the call is executed
        assertTrue(respuesta != null);
        assertTrue(respuesta.isSuccessful());

        //Finish web server
        mockWebServer.shutdown();
    }
}
