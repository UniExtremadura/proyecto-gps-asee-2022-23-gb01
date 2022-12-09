package es.unex.asee_proyectoprueba;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.asee_proyectoprueba.model.Pendings;

public class CU14_AddPendings_UnitTest {

    protected Pendings pendings;

    @Test
    public void shouldGetFilmId(){
        assertEquals(pendings.getFilmID(),1);
    }

    @Test
    public void shouldGetUsername(){
        assertEquals(pendings.getUsername(),"username");
    }

    @Test
    public void shouldSetFilmId(){
        pendings.setFilmID(2);
        assertEquals(pendings.getFilmID(),2);
    }

    @Test
    public void shouldSetUsername(){
        pendings.setUsername("user");
        assertEquals(pendings.getUsername(),"user");
    }

    @Before
    public void initTest(){
        pendings = new Pendings(1,"username");
    }
}
