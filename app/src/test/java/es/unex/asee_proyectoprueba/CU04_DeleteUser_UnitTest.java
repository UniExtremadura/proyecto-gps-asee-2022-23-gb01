package es.unex.asee_proyectoprueba;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.asee_proyectoprueba.model.User;

public class CU04_DeleteUser_UnitTest {

    protected User user;

    @Test
    public void shouldGetUsername() {
        assertEquals(user.getUsername(),"username");
    }

    @Test
    public void shouldSetUsername() {
        user.setUsername("user");
        assertEquals(user.getUsername(),"user");
    }

    @Before
    public void initTest() {
        user = new User ("username","user@email.es","password");
    }
}
