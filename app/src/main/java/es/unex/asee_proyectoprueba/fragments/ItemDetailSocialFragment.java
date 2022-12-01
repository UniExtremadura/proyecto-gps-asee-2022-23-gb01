package es.unex.asee_proyectoprueba.fragments;

import static java.lang.Math.round;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import es.unex.asee_proyectoprueba.sharedinterfaces.ItemDetailInterface;
import es.unex.asee_proyectoprueba.support.AppExecutors;
import es.unex.asee_proyectoprueba.R;
import es.unex.asee_proyectoprueba.support.UserFilmsData;
import es.unex.asee_proyectoprueba.adapters.CommentAdapter;
import es.unex.asee_proyectoprueba.model.Comments;
import es.unex.asee_proyectoprueba.model.Films;
import es.unex.asee_proyectoprueba.model.Rating;
import es.unex.asee_proyectoprueba.room.FilmsDatabase;

public class ItemDetailSocialFragment extends Fragment {

    // Referencias a vistas de la UI
    private ImageView ivMoviePoster;
    private TextView tvMovieTitle;
    private TextView tvMovieDate;
    private TextView tvRatingSocial;
    private EditText etCommentSocial;
    private ImageButton ibSendComment;
    private Button bAddRating;
    private NumberPicker npAddRating;

    // Lista que mantiene la información viva acerca de los comentarios de la película
    private List<Comments> commentList = new ArrayList<>();

    private ItemDetailInterface itemDetailInterface;

    private CommentAdapter commentAdapter;

    Films film;

    SharedPreferences loginPreferences;

    public ItemDetailSocialFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        loginPreferences = getActivity().getSharedPreferences(getActivity().getPackageName() + "_preferences", Context.MODE_PRIVATE);
        View v = inflater.inflate(R.layout.fragment_item_detail_social, container, false);
        View recyclerView = v.findViewById(R.id.social_comments);
        assert recyclerView != null;
        commentAdapter = new CommentAdapter(commentList, loginPreferences.getString("USERNAME", ""), getContext());
        setupRecyclerView((RecyclerView) recyclerView);

        getViewsReferences(v);
        npAddRating.setMinValue(1);
        npAddRating.setMaxValue(10);

        film = itemDetailInterface.getFilmSelected();
        Log.i("Q", "Número de votos al crear social: "+film.getTotalVotesMovieCheck());

        updateUI();

        ibSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentBody = etCommentSocial.getText().toString();
                if (!commentBody.equals("")) {
                    addComment(commentBody);
                    etCommentSocial.setText("");
                } else {
                    Snackbar.make(view, R.string.empty_comment, 2500)
                            .show();
                }
            }
        });

        return v;
    }

    private void getViewsReferences(View view) {
        ivMoviePoster = view.findViewById(R.id.ivMoviePoster);
        tvMovieTitle = view.findViewById(R.id.tvMovieTitle);
        tvMovieDate = view.findViewById(R.id.tvMovieDate);
        tvRatingSocial = view.findViewById(R.id.tvRatingSocial);
        etCommentSocial = view.findViewById(R.id.etCommentSocial);
        ibSendComment = view.findViewById(R.id.ibSendComment);
        bAddRating = view.findViewById(R.id.bAddRating);
        npAddRating = view.findViewById(R.id.npAddRating);
    }

    public void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(commentAdapter);
    }

    private void updateUI() {
        commentList = itemDetailInterface.getCommentList();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvMovieTitle.setText(film.getTitle());
                tvMovieDate.setText(film.getReleaseDate().split("-")[0]);
                Glide.with(getContext()).load("https://image.tmdb.org/t/p/original/" + film.getPosterPath()).into(ivMoviePoster);
                commentAdapter.swap(commentList);
            }
        });
    }

    private void addComment(String commentBody) {
        Comments comment = new Comments(loginPreferences.getString("USERNAME", ""), film.getId(), commentBody);
        commentList.add(comment);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                commentAdapter.swap(commentList);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            itemDetailInterface = (ItemDetailInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement InfoButtonListener");
        }
    }

    @Override
    public void onResume() {
        updateUI();
        super.onResume();
    }
}