package com.dnod.simplemoview.service;

import com.dnod.simplemoview.data.Movie;

import java.util.List;

/**
 * This is a client API behaviour
 */
public interface IClientApi {

    List<Movie> getMovies(int page);

    void getMoviesAsync(int page, Listener<List<Movie>> listener);

    Movie getMovieDetails(String movieId);

    void getMovieDetailsAsync(String movieId, Listener<Movie> listener);

    interface Listener<T> {

        void onDataFetched(T data);

        void onError(String error);
    }
}
