package com.dnod.simplemoview.service.impl.api;

import com.dnod.simplemoview.data.Movie;
import com.dnod.simplemoview.service.IClientApi;

import java.util.List;

public class DefaultClientApiImpl implements IClientApi {

    @Override
    public List<Movie> getMovies(int page) {
        return null;
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
