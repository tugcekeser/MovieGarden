package com.example.rabia.moviegarden.di;

import com.example.rabia.moviegarden.views.MoviesActivity;
import com.example.rabia.moviegarden.views.MoviesActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rabia on 7/8/18.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MoviesActivityModule.class)
    abstract MoviesActivity bindMoviesActivity();
}
