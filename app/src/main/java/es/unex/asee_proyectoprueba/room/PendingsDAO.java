package es.unex.asee_proyectoprueba.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.unex.asee_proyectoprueba.model.Pendings;

@Dao
public interface PendingsDAO {

    @Insert
    void insertPendings(Pendings pendings);

    @Delete
    void deletePendings(Pendings pendings);

    @Query("SELECT filmID FROM Pendings WHERE username = (:username)")
    List<Integer> getFilmsIDPendings(String username);
}
