package es.unex.asee_proyectoprueba;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.asee_proyectoprueba.model.Genre;

public class CU06_GenreTest {
    protected Genre genre;

    @Test
    public void shouldGetId (){
        assertEquals(genre.getId().intValue(),1);
    }

    @Test
    public void shouldGetName (){
        assertEquals(genre.getName(),"genre");
    }

    @Test
    public void shouldSetId (){
        genre.setId(2);
        assertEquals(genre.getId().intValue(),2);
    }

    @Test
    public void shouldSetName (){
        genre.setName("name");
        assertEquals(genre.getName(),"name");
    }

    @Test
    public void shouldCompareTo(){
        Genre aux = new Genre(1,"genre");
        assertEquals(aux.compareTo(genre),0);
    }

    @Before
    public void initTest(){
        genre = new Genre(1,"genre");
    }

}
