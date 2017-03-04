package com.dnod.simplemovie;

import android.app.Application;

import com.dnod.simplemovie.service.IClientApi;
import com.dnod.simplemovie.service.impl.api.MockClientApiImpl;
import com.dnod.simplemovie.utils.TimeUtils;
import com.google.gson.GsonBuilder;

public class SimpleMovieMockController extends Application {

    private static SimpleMovieMockController sInstance;

    private IClientApi mClientApi;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mClientApi = new MockClientApiImpl(sInstance, new GsonBuilder()
                .setDateFormat(TimeUtils.ENDPOINT_DATE_FORMAT)
                .create());
    }

    public static SimpleMovieMockController getInstance() {
        return sInstance;
    }

    public static IClientApi getClientApi() {
        return sInstance.mClientApi;
    }
}
