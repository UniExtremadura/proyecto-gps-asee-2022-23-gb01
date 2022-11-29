package es.unex.asee_proyectoprueba.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

// Clase Films
@Entity(tableName = "Films")
public class Films implements Serializable {

    public Films(int filmID, String title, String poster, String overview, String releaseDate) {
        this.id = filmID;
        this.title = title;
        this.posterPath = poster;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public Films() {
        totalRatingMovieCheck = 0;
        totalVotesMovieCheck = 0;
    }

    // Atributos de la clase Film
    @Ignore
    @SerializedName("adult")
    @Expose
    private Boolean adult;

    @Ignore
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @Ignore
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    @Ignore
    @SerializedName("popularity")
    @Expose
    private Double popularity;

    @SerializedName("original_title")
    @Expose
    private String originalTitle;

    @Ignore
    @SerializedName("video")
    @Expose
    private Boolean video;

    @Ignore
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;


    // ID de película
    @PrimaryKey
    @ColumnInfo(name = "filmID")
    @SerializedName("id")
    @Expose
    private Integer id;

    // Título original de la película

    @SerializedName("title")
    @Expose
    private String title;


    // Generos de la película
    @Ignore
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;

    // Poster de la película
    @ColumnInfo(name = "poster")
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    // Descripción de la película
    @SerializedName("overview")
    @Expose
    private String overview;

    // Fecha de la película
    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    // Valoración media
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    private Integer totalVotesMovieCheck;

    private Integer totalRatingMovieCheck;

    // Métodos SET and GET de la clase pelicula

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public void setTotalVotesMovieCheck(Integer totalVotesMovieCheck) {this.totalVotesMovieCheck = totalVotesMovieCheck;}

    public Integer getTotalVotesMovieCheck() {return totalVotesMovieCheck;}

    public void setTotalRatingMovieCheck(Integer totalRatingMovieCheck) {this.totalRatingMovieCheck = totalRatingMovieCheck;}

    public Integer getTotalRatingMovieCheck() {return totalRatingMovieCheck;}
}