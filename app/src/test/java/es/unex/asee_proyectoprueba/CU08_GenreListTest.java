package es.unex.asee_proyectoprueba;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import es.unex.asee_proyectoprueba.model.Genre;
import es.unex.asee_proyectoprueba.model.GenresList;

public class CU08_GenreListTest {

    protected GenresList genresList;

    protected List<Genre> list;

    @Test
    public void shouldGetGenres(){
        assertEquals(genresList.getGenres(),list);
    }

    @Test
    public void shouldSetGenres(){
        List<Genre> genreslist = new ArrayList<>();
        genresList.setGenres(genreslist);
        assertEquals(genresList.getGenres(),genreslist);
    }

    @Before
    public void initTest(){
        list = new ArrayList<>();
        genresList = new GenresList(list);
    }
}
