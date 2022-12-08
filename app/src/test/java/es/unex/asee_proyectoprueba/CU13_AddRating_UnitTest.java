package es.unex.asee_proyectoprueba;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.asee_proyectoprueba.model.Rating;

public class CU13_AddRating_UnitTest {

    protected Rating rating;

    @Test
    public void shouldGetFilmId(){
        assertEquals(rating.getFilmID(),1);
    }

    @Test
    public void shouldGetUsername(){
        assertEquals(rating.getUsername(),"username");
    }

    @Test
    public void shouldGetRating(){
        assertEquals(rating.getRating(),1);
    }

    @Test
    public void shouldSetFilmId(){
        rating.setFilmID(2);
        assertEquals(rating.getFilmID(),2);
    }

    @Test
    public void shouldSetUsername(){
        rating.setUsername("user");
        assertEquals(rating.getUsername(),"user");
    }

    @Test
    public void shouldSetRating(){
        rating.setRating(2);
        assertEquals(rating.getRating(),2);
    }

    @Before
    public void initTest(){
        rating = new Rating(1,"username",1);
    }
}
