package es.unex.asee_proyectoprueba;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import es.unex.asee_proyectoprueba.model.Films;
import es.unex.asee_proyectoprueba.model.FilmsPages;

public class CU08_FilmsPagesTest {

    protected FilmsPages filmsPages;
    private List<Films> films;

    @Test
    public void shouldGetPage(){
        assertEquals(filmsPages.getPage().intValue(),1);
    }

    @Test
    public void shouldGetTotalResults(){
        assertEquals(filmsPages.getTotalResults().intValue(),1);
    }

    @Test
    public void shouldGetTotalPages(){
        assertEquals(filmsPages.getTotalPages().intValue(),1);
    }

    @Test
    public void shouldGetResults(){
        assertEquals(filmsPages.getResults(),films);
    }

    @Test
    public void shouldSetPage(){
        filmsPages.setPage(2);
        assertEquals(filmsPages.getPage().intValue(),2);
    }

    @Test
    public void shouldSetTotalResults(){
        filmsPages.setTotalResults(2);
        assertEquals(filmsPages.getTotalResults().intValue(),2);
    }

    @Test
    public void shouldSetTotalPages(){
        filmsPages.setTotalPages(2);
        assertEquals(filmsPages.getTotalPages().intValue(),2);
    }

    @Test
    public void shouldSetResults(){
        List<Films> list = new ArrayList<>();
        filmsPages.setResults(list);
        assertEquals(filmsPages.getResults(),list);
    }

    @Before
    public void initTest(){
        films = new ArrayList<>();
        filmsPages = new FilmsPages(1,1,1,films);
    }
}
