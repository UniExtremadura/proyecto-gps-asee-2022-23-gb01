package es.unex.asee_proyectoprueba;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.asee_proyectoprueba.model.Comments;

public class CU03_AddComments_UnitTest {

    protected Comments comments;

    @Test
    public void shouldGetCommentId (){
        assertEquals(comments.getCommentID(),1);
    }

    @Test
    public void shouldGetUsername (){
        assertEquals(comments.getUsername(),"username");
    }

    @Test
    public void shouldGetFilmId (){
        assertEquals(comments.getFilmID(),1);
    }

    @Test
    public void shouldGetText (){
        assertEquals(comments.getText(),"text");
    }

    @Test
    public void shouldSetCommentId  (){
        comments.setCommentID(2);
        assertEquals(comments.getCommentID(),2);
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

    @Test
    public void shouldSetText (){
        comments.setText("comment");
        assertEquals(comments.getText(),"comment");
    }

    @Before
    public void initTest(){
        comments = new Comments(1,"username",1,"text");
    }

}
