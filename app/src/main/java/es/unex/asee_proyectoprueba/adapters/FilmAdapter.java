package es.unex.asee_proyectoprueba.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import es.unex.asee_proyectoprueba.R;
import es.unex.asee_proyectoprueba.model.Films;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.filmsAdapterViewHolder> {
    private List<Films> filmList;
    private final FilmListener filmListener;
    /*
     * Campo para pasarle al Adapter el layout sobre el que actuar. Esto implica que los IDs de las Views de cada layout deben ser iguales (ej: ivMoviePoster)
     * Los layouts 'afectados' son explore_item_grid_content.xml, favorites_item_list_content.xml y pendings_item_list_content.xml
     * Se trata de un Int ya que el ID del layout se resuelve como un entero.
     */
    private final int listItemLayout;

    public FilmAdapter(List<Films> list, int listItemLayout, Context context) {
        this.filmList = list;
        this.listItemLayout = listItemLayout;
        filmListener = (FilmListener) context;
    }

    @NonNull
    @Override
    public filmsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(this.listItemLayout, parent,false);
        return new filmsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull filmsAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(filmList.get(position).getTitle());
        Glide.with(holder.image.getContext()).load("https://image.tmdb.org/t/p/original/"+filmList.get(position).getPosterPath()).into(holder.image);
        holder.date.setText(filmList.get(position).getReleaseDate().split("-")[0]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filmListener.onFilmSelected(filmList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public static class filmsAdapterViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final ImageView image;
        final TextView date;

        filmsAdapterViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tvMovieTitle);
            image = (ImageView) view.findViewById(R.id.ivMoviePoster);
            date = (TextView) view.findViewById(R.id.tvMovieDate);
        }
    }

    public void swap(List<Films> filmList){
        this.filmList = filmList;
        notifyDataSetChanged();
    }

    public interface FilmListener{
        void onFilmSelected(Films film);
    }
}
