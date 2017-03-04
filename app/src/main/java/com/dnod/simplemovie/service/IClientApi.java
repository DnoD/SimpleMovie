package com.dnod.simplemovie.service;

import com.dnod.simplemovie.data.Movie;

import java.util.List;

/**
 * This is a client API behaviour
 */
public interface IClientApi {

    List<Movie> getMovies() throws Exception;

    void getMoviesAsync(Listener<List<Movie>> listener);

    Movie getMovieDetails(String movieId) throws Exception;

    void getMovieDetailsAsync(String movieId, Listener<Movie> listener);

    interface Listener<T> {

        void onDataFetched(T data);

        void onError(String error);
    }
}
