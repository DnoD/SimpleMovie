package com.dnod.simplemoview.service.impl.api;

import android.content.Context;

import com.dnod.simplemoview.R;
import com.dnod.simplemoview.data.Movie;
import com.dnod.simplemoview.service.IClientApi;
import com.dnod.simplemoview.service.impl.api.dto.MovieMockDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MockClientApiImpl implements IClientApi {

    private final Gson mGson;
    private final Context mContext;

    public MockClientApiImpl(Context context, Gson gson) {
        mGson = gson;
        mContext = context;
    }

    @Override
    public List<Movie> getMovies(int page) {
        Type listType = new TypeToken<ArrayList<MovieMockDTO>>() {}.getType();
        InputStream raw = mContext.getResources().openRawResource(R.raw.movies);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        List<MovieMockDTO> dtos = new Gson().fromJson(rd, listType);
        return MoviesMockMarshaller.getInstance().fromEntities(dtos);
    }

    @Override
    public void getMoviesAsync(int page, Listener<List<Movie>> listener) {

    }

    @Override
    public Movie getMovieDetails(String movieId) {
        return null;
    }

    @Override
    public void getMovieDetailsAsync(String movieId, Listener<Movie> listener) {

    }
}
