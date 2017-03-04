package com.dnod.simplemovie.ui.loader;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import com.dnod.simplemovie.SimpleMovieMockController;
import com.dnod.simplemovie.data.Movie;

import java.util.List;

public final class MoviesLoader extends AsyncTaskLoader<MoviesLoader.Result> {

    public MoviesLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Result loadInBackground() {
        Result result = new Result();
        try {
            result.movies = SimpleMovieMockController.getClientApi().getMovies();
        } catch (Exception e) {
            result.error = e.getLocalizedMessage();
        }
        return result;
    }

    public final class Result {
        List<Movie> movies;
        String error;

        public List<Movie> getMovies() {
            return movies;
        }

        public String getError() {
            return error;
        }
    }
}
