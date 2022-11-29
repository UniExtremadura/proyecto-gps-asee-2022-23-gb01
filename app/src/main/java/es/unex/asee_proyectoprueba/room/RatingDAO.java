package es.unex.asee_proyectoprueba.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.unex.asee_proyectoprueba.model.Rating;

@Dao
public interface RatingDAO {

    // Inserta una valoración de un usuario sobre una película
    @Insert
    void insertRating(Rating rating);

    @Query("SELECT filmID FROM Ratings WHERE username = (:username)")
    List<Integer> getRatingIDs(String username);

}
