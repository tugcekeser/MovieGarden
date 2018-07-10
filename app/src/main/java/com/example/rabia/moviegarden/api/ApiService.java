package com.example.rabia.moviegarden.api;

import android.util.Log;

import com.example.rabia.moviegarden.models.RequestPresenter;
import com.example.rabia.moviegarden.models.ResponsePresenter;
import com.example.rabia.moviegarden.models.responses.NowPlayingResponse;
import com.example.rabia.moviegarden.models.responses.VideosResponse;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rabia on 7/8/18.
 */

public class ApiService {

    private final String TAG = ApiService.class.getCanonicalName();
    private static String SERVER_BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";

    private ResponsePresenter responsePresenter;

    private Retrofit retrofit;

    @Inject
    public ApiService() {
        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

            OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
    }

    public void setResponsePresenter(ResponsePresenter responsePresenter) {
        this.responsePresenter = responsePresenter;
    }

    public void fetchNowPlayingMovies() {
        ApiInterface apiInterface =
                retrofit.create(ApiInterface.class);

        Call<NowPlayingResponse> nowPlayingResponseCall =
                apiInterface.fetchNowPlayingMovies(API_KEY);

        nowPlayingResponseCall.enqueue(new Callback<NowPlayingResponse>() {
            @Override
            public void onResponse(Call<NowPlayingResponse> call,
                                   Response<NowPlayingResponse> response) {

                if (response.code() == 200) {
                    final NowPlayingResponse r = response.body();
                    responsePresenter.onDataSetChanged(RequestPresenter.Request.UPCOMING_MOVIES, r);
                } else {
                    responsePresenter.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<NowPlayingResponse> call, Throwable t) {

                Log.d(TAG, "ERROR : " + t.getMessage());
                responsePresenter.onError(t.getMessage());
            }
        });
    }

    public void fetchVideos(int movieId) {

        ApiInterface apiInterface =
                retrofit.create(ApiInterface.class);

        Call<VideosResponse> videosResponseCall =
                apiInterface.fetchVideos(movieId, API_KEY);

        videosResponseCall.enqueue(new Callback<VideosResponse>() {
            @Override
            public void onResponse(Call<VideosResponse> call,
                                   Response<VideosResponse> response) {
                if (response.code() == 200) {
                    final VideosResponse r = response.body();
                    responsePresenter.onDataSetChanged(RequestPresenter.Request.VIDEOS, r);
                } else {
                    responsePresenter.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<VideosResponse> call, Throwable t) {

                Log.d(TAG, "ERROR : " + t.getMessage());
                responsePresenter.onError(t.getMessage());
            }
        });
    }
}
