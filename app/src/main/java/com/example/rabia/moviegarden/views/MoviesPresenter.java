package com.example.rabia.moviegarden.views;

import com.example.rabia.moviegarden.models.data.Result;

import java.util.List;

/**
 * Created by rabia on 7/8/18.
 */

public class MoviesPresenter implements MoviesContract.Presenter, MoviesContract.Model.ModelHandler {

    private MoviesContract.Model model;
    private MoviesContract.View view;

    public MoviesPresenter(MoviesContract.Model model, MoviesContract.View view) {
        this.model = model;
        this.view = view;
        this.model.setModelHandler(this);
    }

    @Override
    public void loadScreen() {
        model.fetchMovies();
    }

    @Override
    public void fetchMovieTrailer(int id) {
        model.fetchVideos(id);
    }

    @Override
    public void moviesFetched(List<Result> resultList) {
        if (view != null) {
            view.updatedMovies(resultList);
        }
    }

    @Override
    public void errorOccurred(String errorMessage) {
        if (view != null) {
            view.showError(errorMessage);
        }
    }

    @Override
    public void trailerUrlFetched(String url) {
        if (view != null) {
            view.showTrailer(url);
        }
    }
}
