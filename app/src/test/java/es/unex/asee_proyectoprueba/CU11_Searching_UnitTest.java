package es.unex.asee_proyectoprueba;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.asee_proyectoprueba.support.LevenshteinSearch;

public class CU11_Searching_UnitTest {

    protected  String a;
    protected  String b;
    protected  int distance;
    protected LevenshteinSearch search;

    @Test
    public void sameCharactersTest () {
       a = "hola";
       b = "hola";

       distance = search.distance(a,b);
       assertEquals(distance,0);
    }

    @Test
    public void differentCharactersTest () {
        a = "hola";
        b = "menu";

        distance = search.distance(a,b);
        assertEquals(distance,4);
    }

    @Test
    public void similarCharactersTest () {
        a = "hola";
        b = "ola";

        distance = search.distance(a,b);
        assertEquals(distance,1);
    }

    @Before
    public void initTest(){
        LevenshteinSearch search = new LevenshteinSearch();
    }
}
