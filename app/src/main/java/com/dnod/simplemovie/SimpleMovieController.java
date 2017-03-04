package com.dnod.simplemovie;

import android.app.Application;

import com.dnod.simplemovie.service.IClientApi;
import com.dnod.simplemovie.service.IImageLoader;
import com.dnod.simplemovie.service.impl.DefaultImageLoaderImpl;
import com.dnod.simplemovie.service.impl.api.DefaultClientApiImpl;

public class SimpleMovieController extends Application {

    private static SimpleMovieController sInstance;

    protected IClientApi mClientApi;
    protected IImageLoader mImageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mClientApi = new DefaultClientApiImpl();
        mImageLoader = new DefaultImageLoaderImpl();
    }

    public static SimpleMovieController getInstance() {
        return sInstance;
    }

    public static IClientApi getClientApi() {
        return sInstance.mClientApi;
    }

    public static IImageLoader getImageLoader() {
        return sInstance.mImageLoader;
    }
}
