package es.unex.asee_proyectoprueba;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.asee_proyectoprueba.model.Comments;

public class CU12_DeleteComments_UnitTest {

    protected Comments comments;

    @Test
    public void shouldGetUsername (){
        assertEquals(comments.getUsername(),"username");
    }

    @Test
    public void shouldGetFilmId (){
        assertEquals(comments.getFilmID(),1);
    }

    @Test
    public void shouldSetUsername (){
        comments.setUsername("user");
        assertEquals(comments.getUsername(),"user");
    }

    @Test
    public void shouldSetFilmId (){
        comments.setFilmID(2);
        assertEquals(comments.getFilmID(),2);
    }

    @Before
    public void initTest(){
        comments = new Comments(1,"username",1,"text");
    }

}
