package es.unex.asee_proyectoprueba.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// Clase User
@Entity(tableName = "Users")
public class User implements Serializable {

    // Atributos de la clase User

    @PrimaryKey @NonNull
    private String username;
    private String email;
    private String password;


    public User() {}

    public User(@NonNull String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Setters y Getters de la clase User

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
