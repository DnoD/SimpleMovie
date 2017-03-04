package com.dnod.simplemovie.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dnod.simplemovie.R;
import com.dnod.simplemovie.SimpleMovieController;
import com.dnod.simplemovie.data.Movie;
import com.dnod.simplemovie.databinding.ActivityMainBinding;
import com.dnod.simplemovie.ui.adapter.MoviesAdapter;
import com.dnod.simplemovie.ui.decor.VerticalSpaceItemDecoration;
import com.dnod.simplemovie.ui.loader.MoviesLoader;
import com.dnod.simplemovie.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        LoaderManager.LoaderCallbacks, MoviesAdapter.Listener {
    private static final String INSTANCE_STATE_KEY = "instance_state";

    private ActivityMainBinding mBindingObject;
    private InstanceState mInstanceState = new InstanceState();
    private MoviesAdapter mMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindingObject = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBindingObject.list.setLayoutManager(new LinearLayoutManager(this));
        mBindingObject.list.addItemDecoration(new VerticalSpaceItemDecoration(
                (int) getResources().getDimension(R.dimen.padding_mini)));
        mBindingObject.list.setAdapter(mMoviesAdapter = new MoviesAdapter(this,
                SimpleMovieController.getImageLoader())
                .setListener(this));
        mBindingObject.swipeContainer.setRefreshing(false);
        mBindingObject.swipeContainer.setOnRefreshListener(this);
        mBindingObject.swipeContainer.setColorSchemeResources(R.color.colorPrimary);
        if (savedInstanceState != null) {
            mInstanceState = savedInstanceState.getParcelable(INSTANCE_STATE_KEY);
        }
        if (mInstanceState.movies.isEmpty()) {
            onRefresh();
        } else {
            mMoviesAdapter.replaceAll(mInstanceState.movies);
        }
        setSupportActionBar(mBindingObject.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }
    }

    @Override
    public void onRefresh() {
        mBindingObject.swipeContainer.setRefreshing(true);
        getSupportLoaderManager().restartLoader(Constants.MOVIES_LOADER_ID, null, this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(INSTANCE_STATE_KEY, mInstanceState);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        if (id == Constants.MOVIES_LOADER_ID)
            return new MoviesLoader(this);
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        if (loader.getId() == Constants.MOVIES_LOADER_ID) {
            mBindingObject.swipeContainer.setRefreshing(false);
            MoviesLoader.Result result = (MoviesLoader.Result) data;
            if (result.getError() == null) {
                if (result.getMovies() == null || result.getMovies().isEmpty()) {
                    mBindingObject.emptyContentMessage.setVisibility(View.VISIBLE);
                } else {
                    mInstanceState.movies = result.getMovies();
                    mMoviesAdapter.replaceAll(mInstanceState.movies);
                    mBindingObject.emptyContentMessage.setVisibility(View.GONE);
                }
            } else {
                mBindingObject.emptyContentMessage.setVisibility(View.VISIBLE);
                Snackbar.make(mBindingObject.getRoot(), result.getError(), Snackbar.LENGTH_LONG)
                        .setAction(R.string.action_retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onRefresh();
                            }
                        }).show();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    @Override
    public void onItemClick(Movie movie) {
        startActivity(MovieDetailsActivity.createIntent(this, movie));
    }

    private static final class InstanceState implements Parcelable {
        List<Movie> movies = new ArrayList<>();

        InstanceState() {
        }

        InstanceState(Parcel in) {
            movies = in.createTypedArrayList(Movie.CREATOR);
        }

        public static final Creator<InstanceState> CREATOR = new Creator<InstanceState>() {
            @Override
            public InstanceState createFromParcel(Parcel in) {
                return new InstanceState(in);
            }

            @Override
            public InstanceState[] newArray(int size) {
                return new InstanceState[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeTypedList(movies);
        }
    }
}
