package com.dnod.simplemovie;

import com.dnod.simplemovie.service.IClientApi;
import com.dnod.simplemovie.service.IImageLoader;
import com.dnod.simplemovie.service.impl.DefaultImageLoaderImpl;
import com.dnod.simplemovie.service.impl.api.MockClientApiImpl;
import com.dnod.simplemovie.utils.TimeUtils;
import com.google.gson.GsonBuilder;

public class SimpleMovieMockController extends SimpleMovieController {

    private static SimpleMovieMockController sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mClientApi = new MockClientApiImpl(sInstance, new GsonBuilder()
                .setDateFormat(TimeUtils.ENDPOINT_DATE_FORMAT)
                .create());
        mImageLoader = new DefaultImageLoaderImpl();
    }

    public static IClientApi getClientApi() {
        return sInstance.mClientApi;
    }

    public static IImageLoader getImageLoader() {
        return sInstance.mImageLoader;
    }
}
