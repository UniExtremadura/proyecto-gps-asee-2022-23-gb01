package es.unex.asee_proyectoprueba.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "FilmsGenresList",primaryKeys = {"filmID","genreID"})
public class FilmsGenresList {
    @ColumnInfo(name="filmID")
    private int filmID;
    @ColumnInfo(name="genreID")
    private int genreID;

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public FilmsGenresList() {
    }

    public FilmsGenresList(int filmid, int genreid) {
        filmID = filmid;
        genreID = genreid;
    }
}
