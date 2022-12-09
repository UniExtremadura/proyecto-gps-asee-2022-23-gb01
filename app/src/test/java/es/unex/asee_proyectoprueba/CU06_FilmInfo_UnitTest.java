package es.unex.asee_proyectoprueba;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import es.unex.asee_proyectoprueba.model.Films;

public class CU06_FilmInfo_UnitTest {

    protected Films films;
    protected List<Integer> genresids;

    @Test
    public void shouldGetAdult (){
        assertEquals(films.getAdult(),true);
    }

    @Test
    public void shouldGetBackdropPath (){
        assertEquals(films.getBackdropPath(),"bk_poster");
    }

    @Test
    public void shouldGetOriginalLanguage (){
        assertEquals(films.getOriginalLanguage(),"language");
    }

    @Test
    public void shouldGetPopularity (){
        assertEquals(films.getPopularity(),0.0,0.00000001);
    }

    @Test
    public void shouldGetOriginalTitle (){
        assertEquals(films.getOriginalTitle(),"originalTitle");
    }

    @Test
    public void shouldGetVideo (){
        assertEquals(films.getVideo(),false);
    }

    @Test
    public void shouldGetVoteCount (){
        assertEquals(films.getVoteCount().intValue(),0);
    }

    @Test
    public void shouldGetId (){
        assertEquals(films.getId().intValue(),0);
    }

    @Test
    public void shouldGetTitle (){
        assertEquals(films.getTitle(),"title");
    }

    @Test
    public void shouldGetGenresIds (){
        assertEquals(films.getGenreIds(),genresids);
    }

    @Test
    public void shouldGetPosterPath (){
        assertEquals(films.getPosterPath(),"poster");
    }

    @Test
    public void shouldGetOverview (){
        assertEquals(films.getOverview(),"overview");
    }

    @Test
    public void shouldGetRealeaseDate (){
        assertEquals(films.getReleaseDate(),"date");
    }

    @Test
    public void shouldGetVoteAverage (){
        assertEquals(films.getVoteAverage(),0.0,0.00000001);
    }

    @Test
    public void shouldGetTotalVotesMovieCheck (){
        assertEquals(films.getTotalVotesMovieCheck().intValue(),0);
    }

    @Test
    public void shouldGetTotalRatingMovieCheck (){
        assertEquals(films.getTotalRatingMovieCheck().intValue(),0);
    }

    @Test
    public void shouldSetAdult (){
        films.setAdult(false);
        assertEquals(films.getAdult(),false);
    }

    @Test
    public void shouldSetBackdropPath (){
        films.setBackdropPath("bk_path");
        assertEquals(films.getBackdropPath(),"bk_path");
    }

    @Test
    public void shouldSetOriginalLanguage (){
        films.setOriginalLanguage("og_language");
        assertEquals(films.getOriginalLanguage(),"og_language");
    }

    @Test
    public void shouldSetPopularity (){
        films.setPopularity(1.1);
        assertEquals(films.getPopularity(),1.1,0.00000001);
    }

    @Test
    public void shouldSetOriginalTitle (){
        films.setOriginalTitle("og_title");
        assertEquals(films.getOriginalTitle(),"og_title");
    }

    @Test
    public void shouldSetVideo (){
        films.setAdult(true);
        assertEquals(films.getAdult(),true);
    }

    @Test
    public void shouldSetVoteCount (){
        films.setVoteCount(1);
        assertEquals(films.getVoteCount().intValue(),1);
    }

    @Test
    public void shouldSetId (){
        films.setId(1);
        assertEquals(films.getId().intValue(),1);
    }

    @Test
    public void shouldSetTitle (){
        films.setTitle("f_title");
        assertEquals(films.getTitle(),"f_title");
    }

    @Test
    public void shouldSetGenresIds (){
        List<Integer> list = new ArrayList<>();
        films.setGenreIds(list);
        assertEquals(films.getGenreIds(),genresids);
    }

    @Test
    public void shouldSetPosterPath (){
        films.setPosterPath("path");
        assertEquals(films.getPosterPath(),"path");
    }

    @Test
    public void shouldSetOverview (){
        films.setOverview("f_overview");
        assertEquals(films.getOverview(),"f_overview");
    }

    @Test
    public void shouldSetRealeaseDate (){
        films.setReleaseDate("f_date");
        assertEquals(films.getReleaseDate(),"f_date");
    }

    @Test
    public void shouldSetVoteAverage (){
        films.setVoteAverage(1.1);
        assertEquals(films.getVoteAverage(),1.1,0.00000001);
    }

    @Test
    public void shouldSetTotalVotesMovieCheck (){
        films.setTotalVotesMovieCheck(1);
        assertEquals(films.getTotalVotesMovieCheck().intValue(),1);
    }

    @Test
    public void shouldSetTotalRatingMovieCheck (){
        films.setTotalRatingMovieCheck(1);
        assertEquals(films.getTotalRatingMovieCheck().intValue(),1);
    }

    @Before
    public void initTest(){
        genresids = new ArrayList<>();
        films = new Films(true, "bk_poster", "language", 0.0,"originalTitle",false,0,0,
                "title",genresids,"poster","overview","date",0.0,0,0);
    }
}
