package es.unex.asee_proyectoprueba;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.asee_proyectoprueba.model.FilmsGenresList;

public class CU08_FilmsGenresListTest {

    protected FilmsGenresList filmsGenresList;

    @Test
    public void shouldGetFilmId (){
        assertEquals(filmsGenresList.getFilmID(),1);
    }

    @Test
    public void shouldGetGenreId (){
        assertEquals(filmsGenresList.getGenreID(),1);
    }

    @Test
    public void shouldSetFilmId (){
        filmsGenresList.setFilmID(2);
        assertEquals(filmsGenresList.getFilmID(),2);
    }

    @Test
    public void shouldSetGenreId (){
        filmsGenresList.setGenreID(2);
        assertEquals(filmsGenresList.getGenreID(),2);
    }

    @Before
    public void initTest() {
        filmsGenresList = new FilmsGenresList(1,1);
    }
}
