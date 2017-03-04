package com.dnod.simplemovie.service.impl.api;

import android.content.Context;

import com.dnod.simplemovie.R;
import com.dnod.simplemovie.data.Movie;
import com.dnod.simplemovie.service.IClientApi;
import com.dnod.simplemovie.service.impl.api.dto.MovieMockDTO;
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
    public List<Movie> getMovies() {
        Type listType = new TypeToken<ArrayList<MovieMockDTO>>() {}.getType();
        InputStream raw = mContext.getResources().openRawResource(R.raw.movies);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        List<MovieMockDTO> dtos = mGson.fromJson(rd, listType);
        return MoviesMockMarshaller.getInstance().fromEntities(dtos);
    }

    @Override
    public void getMoviesAsync(Listener<List<Movie>> listener) {
        //Ignore for this version
    }

    @Override
    public Movie getMovieDetails(String movieId) {
        InputStream raw = mContext.getResources().openRawResource(R.raw.movie_details);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        MovieMockDTO dto = mGson.fromJson(rd, MovieMockDTO.class);
        return MoviesMockMarshaller.getInstance().fromEntity(dto);
    }

    @Override
    public void getMovieDetailsAsync(String movieId, Listener<Movie> listener) {
        //Ignore for this version
    }
}
