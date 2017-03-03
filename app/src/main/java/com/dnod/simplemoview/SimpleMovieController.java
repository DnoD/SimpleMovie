package com.dnod.simplemoview;

import android.app.Application;

import com.dnod.simplemoview.service.IClientApi;
import com.dnod.simplemoview.service.impl.api.DefaultClientApiImpl;

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

    public IClientApi getClientApi() {
        return mClientApi;
    }
}
