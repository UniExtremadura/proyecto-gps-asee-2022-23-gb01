package es.unex.asee_proyectoprueba.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

// Clase Favorites
@Entity(tableName = "Favorites", primaryKeys = {"filmID","username"},foreignKeys = {@ForeignKey(entity = Films.class, parentColumns = "filmID", childColumns = "filmID", onDelete = ForeignKey.CASCADE),
                                            @ForeignKey(entity = User.class, parentColumns = "username", childColumns = "username", onDelete = ForeignKey.CASCADE)})
public class Favorites {

    // Atributos Favorites
    @NonNull
    @ColumnInfo(name="filmID")
    private int filmID;
    @NonNull
    @ColumnInfo(name="username")
    private String username;

    public Favorites(int filmID, @NonNull String username) {
        this.filmID = filmID;
        this.username = username;
    }

    // Métodos SET and GET de la clase Favorites
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

}
