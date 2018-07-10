package com.example.rabia.moviegarden.base;

import android.app.Application;
import android.content.Context;

import com.example.rabia.moviegarden.api.ApiService;
import com.example.rabia.moviegarden.utils.NetworkUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rabia on 7/8/18.
 */

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    ApiService provideApiServices() {
        return new ApiService();
    }

    @Provides
    @Singleton
    NetworkUtils provideNetworkUtils() {
        return new NetworkUtils(application.getApplicationContext());
    }
}
