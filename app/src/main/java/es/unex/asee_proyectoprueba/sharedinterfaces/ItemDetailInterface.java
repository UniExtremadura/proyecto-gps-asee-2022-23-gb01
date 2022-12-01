package es.unex.asee_proyectoprueba.sharedinterfaces;

import java.util.List;

import es.unex.asee_proyectoprueba.model.Comments;
import es.unex.asee_proyectoprueba.model.Films;

// Interfaz para comunicar este Fragment con su actividad (ItemDetailActivity)
public interface ItemDetailInterface {
    Films getFilmSelected();
    List<Comments> getCommentList();
}
