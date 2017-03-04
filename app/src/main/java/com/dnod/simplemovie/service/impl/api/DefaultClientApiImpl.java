package com.dnod.simplemovie.service.impl.api;

import com.dnod.simplemovie.data.Movie;
import com.dnod.simplemovie.service.IClientApi;

import java.util.List;

public class DefaultClientApiImpl implements IClientApi {

    @Override
    public List<Movie> getMovies() {
        return null;
    }

    @Override
    public void getMoviesAsync(Listener<List<Movie>> listener) {

    }

    @Override
    public Movie getMovieDetails(String movieId) {
        return null;
    }

    @Override
    public void getMovieDetailsAsync(String movieId, Listener<Movie> listener) {

    }
}
