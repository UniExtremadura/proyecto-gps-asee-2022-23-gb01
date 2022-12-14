package es.unex.asee_proyectoprueba.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import es.unex.asee_proyectoprueba.model.Comments;

@Dao
public interface CommentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllComment(List<Comments> commentsList);

    @Query("DELETE FROM Comments WHERE filmID = (:filmID) AND username = (:username)")
    void deleteCommentsUserFilm(int filmID, String username);

    @Query("SELECT * FROM Comments WHERE filmID = (:filmID)")
    List<Comments> getFilmComments(int filmID);

}
