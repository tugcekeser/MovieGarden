package com.example.rabia.moviegarden.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by rabia on 7/9/18.
 */

public interface ResponsePresenter<TAction> {

    void onDataSetChanged(@NonNull TAction action, @Nullable Object response);

    void onError(@NonNull String errorMessage);
}
