package com.dnod.simplemovie;

import android.app.Application;

import com.dnod.simplemovie.service.IClientApi;
import com.dnod.simplemovie.service.impl.api.DefaultClientApiImpl;

public class SimpleMovieController extends Application {

    private static SimpleMovieController sInstance;

    private IClientApi mClientApi;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mClientApi = new DefaultClientApiImpl();
    }

    public static SimpleMovieController getInstance() {
        return sInstance;
    }

    public static IClientApi getClientApi() {
        return sInstance.mClientApi;
    }
}
