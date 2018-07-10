package com.example.rabia.moviegarden.views;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.rabia.moviegarden.R;
import com.example.rabia.moviegarden.models.data.Result;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class MoviesActivity extends AppCompatActivity implements MoviesContract.View, AdapterMovies.MoviesListener {

    @Inject
    MoviesContract.Presenter presenter;

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;

    private AdapterMovies adapterMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        presenter.loadScreen();
    }

    @Override
    public void updatedMovies(List<Result> resultList) {
        adapterMovies = new AdapterMovies(this, resultList, this);
        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        rvMovies.setAdapter(adapterMovies);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTrailer(String videoId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + videoId));
        startActivity(intent);
    }

    @Override
    public void onPlayClicked(int movieId) {
        presenter.fetchMovieTrailer(movieId);
    }
}
