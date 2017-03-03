package com.dnod.simplemoview;

import android.app.Application;

import com.dnod.simplemoview.service.IClientApi;
import com.dnod.simplemoview.service.impl.api.MockClientApiImpl;
import com.dnod.simplemoview.utils.TimeUtils;
import com.google.gson.Gson;
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

    public IClientApi getClientApi() {
        return mClientApi;
    }
}
