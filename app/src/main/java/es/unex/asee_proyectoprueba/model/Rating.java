package es.unex.asee_proyectoprueba.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

// Clase valoración
@Entity(tableName = "Ratings",primaryKeys = {"filmID","username"},foreignKeys = {@ForeignKey(entity = Films.class, parentColumns = "filmID", childColumns = "filmID", onDelete = ForeignKey.CASCADE),
                                                                                @ForeignKey(entity = User.class, parentColumns = "username", childColumns = "username", onDelete = ForeignKey.CASCADE)})
public class Rating {

    // Atributos clase valoración
    @ColumnInfo(name="filmID")
    private int filmID;
    @NonNull
    @ColumnInfo(name="username")
    private String username;
    private int rating;

    public Rating(int filmID, @NonNull String username, int rating){
        this.filmID = filmID;
        this.username = username;
        this.rating = rating;
    }

    // Métodos SET and GET de valoración
    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
