package es.unex.asee_proyectoprueba.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.unex.asee_proyectoprueba.model.User;

@Dao
public interface UserDAO {

    // Inserta un usuario en la BD
    @Insert
    void insertUser(User user);

    // Elimina un usuario de la BD dado su nombre de usuario
    @Query("DELETE FROM Users WHERE username = (:username)")
    void deleteUserByID(String username);

    // Devuelve un usuario dado su nombre de usuario
    @Query("SELECT * FROM Users WHERE username = (:username)")
    User getUser(String username);

    // Devuelve una lista con todos los usuarios almacenados
    @Query("SELECT * FROM Users")
    List<User> getAllUsers();
}
