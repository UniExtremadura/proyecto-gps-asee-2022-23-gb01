package es.unex.asee_proyectoprueba.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import es.unex.asee_proyectoprueba.model.Comments;
import es.unex.asee_proyectoprueba.model.Favorites;
import es.unex.asee_proyectoprueba.model.Films;
import es.unex.asee_proyectoprueba.model.FilmsGenresList;
import es.unex.asee_proyectoprueba.model.Genre;
import es.unex.asee_proyectoprueba.model.Pendings;
import es.unex.asee_proyectoprueba.model.Rating;
import es.unex.asee_proyectoprueba.model.User;

@Database(entities = {Films.class, User.class, Genre.class, Rating.class, Comments.class, Favorites.class, Pendings.class, FilmsGenresList.class}, version = 1)
public abstract class FilmsDatabase extends RoomDatabase {
    public static  volatile FilmsDatabase instance;
    public static FilmsDatabase getInstance(Context context){
        if(instance == null){
            synchronized (FilmsDatabase.class){
                if(instance==null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),FilmsDatabase.class,"films.db").build();
                }
            }
        }
        return instance;
    }

    // DAOS
    public abstract FilmDAO filmDAO();
    public abstract FavoritesDAO favoritesDAO();
    public abstract PendingsDAO pendingsDAO();
    public abstract GenreDAO genreDAO();
    public abstract UserDAO userDAO();
    public abstract RatingDAO ratingDAO();
    public abstract CommentDAO commentDAO();
    public abstract FilmsGenresListDAO filmsGenresListDAO();
}

