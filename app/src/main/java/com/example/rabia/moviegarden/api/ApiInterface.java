package com.example.rabia.moviegarden.api;

import com.example.rabia.moviegarden.models.responses.NowPlayingResponse;
import com.example.rabia.moviegarden.models.responses.VideosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rabia on 7/9/18.
 */

public interface ApiInterface {

    @GET("now_playing")
    Call<NowPlayingResponse> fetchNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("{id}/videos")
    Call<VideosResponse> fetchVideos(@Path("id") int id, @Query("api_key") String apiKey);
}
