package com.example.rabia.moviegarden.base;

import com.example.rabia.moviegarden.di.ActivityBuilder;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by rabia on 7/8/18.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        AndroidInjectionModule.class,
        ActivityBuilder.class
})
public interface ApplicationComponent {

    void inject(App app);
}