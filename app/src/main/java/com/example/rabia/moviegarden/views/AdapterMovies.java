package com.example.rabia.moviegarden.views;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.rabia.moviegarden.R;
import com.example.rabia.moviegarden.models.data.Result;

import java.util.List;

/**
 * Created by rabia on 7/9/18.
 */

public class AdapterMovies extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ITEM_TRAILER = 1;
    private final int ITEM_POSTER = 2;
    private static String IMAGE_PATH = "https://image.tmdb.org/t/p/w500/";

    private List<Result> movies;
    private MoviesListener moviesListener;
    private Context context;

    public interface MoviesListener {
        void onPlayClicked(int movieId);
    }

    public AdapterMovies(final Context context, final List<Result> movies, final MoviesListener moviesListener) {
        this.context = context;
        this.movies = movies;
        this.moviesListener = moviesListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {

            case ITEM_POSTER: {
                final View viewHeader = inflater.inflate(R.layout.item_movie_poster, parent, false);
                viewHolder = new ViewHolderPosters(viewHeader);
                break;
            }
            case ITEM_TRAILER: {
                final View viewHeader = inflater.inflate(R.layout.item_movie_trailer, parent, false);
                viewHolder = new ViewHolderTrailers(viewHeader);
                break;
            }
            default: {
                viewHolder = null;
            }

        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        Result movie = movies.get(position);
        switch (viewType) {
            case ITEM_POSTER: {
                setItemPosterViews((ViewHolderPosters) holder, movie);
                break;
            }
            case ITEM_TRAILER: {
                setItemTrailerViews((ViewHolderTrailers) holder, movie);
                break;
            }
        }
    }

    private void setItemPosterViews(ViewHolderPosters viewHolderPosters, Result movie) {

        Glide.with(context).load(IMAGE_PATH + movie.getPosterPath())
                .dontAnimate()
                .into(viewHolderPosters.getIvPoster());

        viewHolderPosters.getTvTitle().setText(movie.getOriginalTitle());
        viewHolderPosters.getTvDesc().setText(movie.getOverview());
    }

    private void setItemTrailerViews(ViewHolderTrailers viewHolderTrailers, final Result movie) {

        Glide.with(context).load(IMAGE_PATH + movie.getBackdropPath())
                .dontAnimate()
                .into(viewHolderTrailers.getIvPoster());

        viewHolderTrailers.getTvTitle().setText(movie.getOriginalTitle());
        viewHolderTrailers.getTvDesc().setText(movie.getOverview());
        viewHolderTrailers.getIvPlay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesListener.onPlayClicked(movie.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return ITEM_TRAILER;
        } else {
            return ITEM_POSTER;
        }
    }
}
