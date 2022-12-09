package es.unex.asee_proyectoprueba;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import es.unex.asee_proyectoprueba.model.Films;
import es.unex.asee_proyectoprueba.support.UserFilmsData;

public class CU15_DeletePendings_UnitTest {
    protected UserFilmsData filmsData;
    protected Films film1;
    protected Films film2;
    protected Films film3;
    protected List<Integer> genresids;

    @Test
    public void pendingsListTest(){
        filmsData.userPendingFilms.put(1,film1);
        filmsData.userPendingFilms.put(2,film2);
        filmsData.userPendingFilms.put(3,film3);

        assertEquals(filmsData.userPendingFilms.size(),3);

        Films fAux;
        fAux = filmsData.userPendingFilms.get(1);
        assertEquals(fAux.getTitle(),"film1");

        fAux = filmsData.userPendingFilms.get(2);
        assertEquals(fAux.getTitle(),"film2");

        fAux = filmsData.userPendingFilms.get(3);
        assertEquals(fAux.getTitle(),"film3");

        filmsData.userPendingFilms.clear();
        assertEquals(filmsData.userPendingFilms.size(),0);
        assertEquals(filmsData.userPendingFilms.isEmpty(),true);

    }

    @Before
    public void initTest(){
        filmsData = UserFilmsData.getInstance();
        genresids = new ArrayList<>();
        film1 = new Films(true, "bk_poster", "language", 0.0,"originalTitle",false,0,1,
                "film1",genresids,"poster","overview","date",0.0,0,0);

        film2 = new Films(true, "bk_poster", "language", 0.0,"originalTitle",false,0,2,
                "film2",genresids,"poster","overview","date",0.0,0,0);

        film3 = new Films(true, "bk_poster", "language", 0.0,"originalTitle",false,0,3,
                "film3",genresids,"poster","overview","date",0.0,0,0);
    }
}
