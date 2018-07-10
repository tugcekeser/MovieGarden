package com.example.rabia.moviegarden.views;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.rabia.moviegarden.api.ApiService;
import com.example.rabia.moviegarden.models.RequestPresenter;
import com.example.rabia.moviegarden.models.ResponsePresenter;
import com.example.rabia.moviegarden.models.responses.NowPlayingResponse;
import com.example.rabia.moviegarden.models.responses.VideosResponse;
import com.example.rabia.moviegarden.utils.NetworkUtils;

/**
 * Created by rabia on 7/9/18.
 */

public class MoviesModel implements MoviesContract.Model, ResponsePresenter {

    private ApiService apiService;
    private NetworkUtils networkUtils;
    private MoviesContract.Model.ModelHandler modelHandler;

    public MoviesModel(ApiService apiService, NetworkUtils networkUtils) {
        this.apiService = apiService;
        this.apiService.setResponsePresenter(this);
        this.networkUtils = networkUtils;
    }

    public void setModelHandler(ModelHandler modelHandler) {
        this.modelHandler = modelHandler;
    }

    @Override
    public void onDataSetChanged(@NonNull Object o, @Nullable Object response) {
        RequestPresenter.Request request = (RequestPresenter.Request) o;

        switch (request) {
            case UPCOMING_MOVIES: {
                NowPlayingResponse nowPlayingResponse = (NowPlayingResponse) response;
                modelHandler.moviesFetched(nowPlayingResponse.getResults());
                break;
            }
            case VIDEOS: {
                VideosResponse videosResponse = (VideosResponse) response;
                modelHandler.trailerUrlFetched(videosResponse.getResults().get(0).getKey());
                break;
            }
        }
    }

    @Override
    public void onError(@NonNull String errorMessage) {
        modelHandler.errorOccurred(errorMessage);
    }

    @Override
    public void fetchMovies() {
        if (networkUtils.isInternetAvailable()) {
            apiService.fetchNowPlayingMovies();
        } else {
            modelHandler.errorOccurred("Network Error");
        }
    }

    @Override
    public void fetchVideos(int id) {
        if (networkUtils.isInternetAvailable()) {
            apiService.fetchVideos(id);
        } else {
            modelHandler.errorOccurred("Network Error");
        }
    }
}
