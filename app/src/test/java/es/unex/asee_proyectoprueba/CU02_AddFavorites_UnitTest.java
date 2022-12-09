package es.unex.asee_proyectoprueba;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.asee_proyectoprueba.model.Favorites;

public class CU02_AddFavorites_UnitTest {

    protected Favorites favorites;

    @Test
    public void shouldGetFilmId(){
        assertEquals(favorites.getFilmID(),1);
    }

    @Test
    public void shouldGetUsername(){
        assertEquals(favorites.getUsername(),"username");
    }

    @Test
    public void shouldSetFilmId(){
        favorites.setFilmID(2);
        assertEquals(favorites.getFilmID(),2);
    }

    @Test
    public void shouldSetUsername(){
        favorites.setUsername("user");
        assertEquals(favorites.getUsername(),"user");
    }

    @Before
    public void initTest(){
        favorites = new Favorites(1,"username");
    }
}
