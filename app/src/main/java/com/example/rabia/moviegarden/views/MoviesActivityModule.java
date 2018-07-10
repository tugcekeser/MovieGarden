package com.example.rabia.moviegarden.views;

import com.example.rabia.moviegarden.api.ApiService;
import com.example.rabia.moviegarden.utils.NetworkUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rabia on 7/8/18.
 */

@Module
public class MoviesActivityModule {

    @Provides
    public MoviesContract.View providesMoviesView(MoviesActivity moviesActivity) {
        return moviesActivity;
    }

    @Provides
    public MoviesContract.Presenter providesMoviesPresenter(MoviesContract.Model model, MoviesContract.View view) {
        return new MoviesPresenter(model, view);
    }

    @Provides
    public MoviesContract.Model providesMoviesModel(ApiService apiService, NetworkUtils networkUtils) {
        return new MoviesModel(apiService, networkUtils);
    }

}
