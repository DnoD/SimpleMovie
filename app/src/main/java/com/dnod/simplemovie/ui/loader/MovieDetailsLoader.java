package com.dnod.simplemovie.ui.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.dnod.simplemovie.SimpleMovieMockController;
import com.dnod.simplemovie.data.Movie;

import java.util.List;

public final class MovieDetailsLoader extends AsyncTaskLoader<MovieDetailsLoader.Result> {

    private final String mMovieId;

    public MovieDetailsLoader(Context context, String movieId) {
        super(context);
        mMovieId = movieId;
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
            result.movie = SimpleMovieMockController.getClientApi().getMovieDetails(mMovieId);
        } catch (Exception e) {
            result.error = e.getLocalizedMessage();
        }
        return result;
    }

    public final class Result {
        Movie movie;
        String error;

        public Movie getMovie() {
            return movie;
        }

        public String getError() {
            return error;
        }
    }
}
