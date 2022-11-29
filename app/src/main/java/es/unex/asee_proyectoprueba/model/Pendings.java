package es.unex.asee_proyectoprueba.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

// Clase pendientes
@Entity(tableName = "Pendings",primaryKeys = {"filmID","username"}, foreignKeys = @ForeignKey(entity = User.class, parentColumns = "username", childColumns = "username", onDelete = ForeignKey.CASCADE))
public class Pendings {

    // Atrbutos pendientes
    @NonNull
    @ColumnInfo(name="filmID")
    private int filmID;
    @NonNull
    @ColumnInfo(name="username")
    private String username;

    public Pendings(){

    }

    public Pendings(int filmID, @NonNull String username) {
        this.filmID = filmID;
        this.username = username;
    }

    // Métodos SET and GET de la clase pendientes
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
