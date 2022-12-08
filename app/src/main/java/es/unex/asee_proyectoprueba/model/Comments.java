package es.unex.asee_proyectoprueba.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

// Clase Comments
@Entity(tableName = "Comments",foreignKeys = {@ForeignKey(entity = Films.class, parentColumns = "filmID", childColumns = "filmID", onDelete = ForeignKey.CASCADE),
                                            @ForeignKey(entity = User.class, parentColumns = "username", childColumns = "username", onDelete = ForeignKey.CASCADE)})
public class Comments {

    // Atributos de la clase Comments
    @PrimaryKey(autoGenerate = true)
    private int commentID;
    private String username;
    private int filmID;
    private String text;

    public Comments(String username, int filmID, String text) {
        this.username = username;
        this.filmID = filmID;
        this.text = text;
    }

    public Comments(int commentid, String username, int filmID, String text) {
        this.commentID = commentid;
        this.username = username;
        this.filmID = filmID;
        this.text = text;
    }


    // Métodos SET and GET de la clase comentario
    public int getCommentID() {
        return commentID;
    }
    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setFilmID(int filmID) {this.filmID = filmID;}
    public int getFilmID() {return filmID;}

}
