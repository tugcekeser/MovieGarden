package com.example.rabia.moviegarden.views;

import com.example.rabia.moviegarden.models.data.Result;

import java.util.List;

/**
 * Created by rabia on 7/8/18.
 */

public interface MoviesContract {

    interface Model {

        interface ModelHandler {
            void moviesFetched(List<Result> resultList);

            void errorOccurred(String errorMessage);

            void trailerUrlFetched(String url);
        }

        void setModelHandler(ModelHandler modelHandler);

        void fetchMovies();

        void fetchVideos(int id);
    }

    interface View {

        void updatedMovies(List<Result> resultList);

        void showError(String errorMessage);

        void showTrailer(String url);
    }

    interface Presenter {

        void loadScreen();

        void fetchMovieTrailer(int id);
    }

}
