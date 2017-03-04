package com.dnod.simplemovie.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dnod.simplemovie.R;
import com.dnod.simplemovie.SimpleMovieController;
import com.dnod.simplemovie.data.Movie;
import com.dnod.simplemovie.databinding.ActivityMainBinding;
import com.dnod.simplemovie.databinding.ActivityMovieDetailsBinding;
import com.dnod.simplemovie.service.IImageLoader;
import com.dnod.simplemovie.ui.adapter.MoviesAdapter;
import com.dnod.simplemovie.ui.decor.VerticalSpaceItemDecoration;
import com.dnod.simplemovie.ui.loader.MovieDetailsLoader;
import com.dnod.simplemovie.ui.loader.MoviesLoader;
import com.dnod.simplemovie.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {
    private static final String INSTANCE_STATE_KEY = "instance_state";
    private static final String PROVIDED_MOVIE = "provided_movie";

    public static Intent createIntent(Context context, @NonNull Movie movie) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(PROVIDED_MOVIE, movie);
        return intent;
    }

    private ActivityMovieDetailsBinding mBindingObject;
    private InstanceState mInstanceState = new InstanceState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindingObject = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        if (savedInstanceState != null) {
            mInstanceState = savedInstanceState.getParcelable(INSTANCE_STATE_KEY);
        } else {
            mInstanceState.movie = getIntent().getParcelableExtra(PROVIDED_MOVIE);
            getSupportLoaderManager().restartLoader(Constants.MOVIE_DETAILS_LOADER_ID, null, this);
        }
        showModel();
        setSupportActionBar(mBindingObject.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(mInstanceState.movie.getTitle());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        showScreenshots();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(INSTANCE_STATE_KEY, mInstanceState);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        if (id == Constants.MOVIE_DETAILS_LOADER_ID)
            return new MovieDetailsLoader(this, mInstanceState.movie.getId());
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        if (loader.getId() == Constants.MOVIE_DETAILS_LOADER_ID) {
            MovieDetailsLoader.Result result = (MovieDetailsLoader.Result) data;
            if (result.getError() == null) {
                if (result.getMovie() != null) {
                    mInstanceState.movie = result.getMovie();
                    showModel();
                    showScreenshots();
                }
            } else {
                Snackbar.make(mBindingObject.getRoot(), result.getError(), Snackbar.LENGTH_LONG)
                        .setAction(R.string.action_retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getSupportLoaderManager().restartLoader(Constants.MOVIES_LOADER_ID,
                                        null, MovieDetailsActivity.this);
                            }
                        }).show();
            }
        }
    }

    private void showModel() {
        mBindingObject.setModel(mInstanceState.movie);
        SimpleMovieController.getImageLoader().displayImage(mBindingObject.cover,
                new IImageLoader.LoadingBuilder().setUrl(mInstanceState.movie.getImages().getOriginalUrl())
                        .setPlaceHolder(R.drawable.image_placeholder));
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showScreenshots() {
        if (mInstanceState.movie.getScreenShots().isEmpty()) {
            mBindingObject.emptyImagesMessage.setVisibility(View.VISIBLE);
        } else {
            mBindingObject.emptyImagesMessage.setVisibility(View.GONE);
            mBindingObject.imagesPager.setAdapter(new PagerAdapter() {

                @Override
                public int getCount() {
                    return mInstanceState.movie.getScreenShots().size();
                }

                @Override
                public Object instantiateItem(ViewGroup collection, int position) {
                    ImageView imageView = new ImageView(MovieDetailsActivity.this);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    SimpleMovieController.getImageLoader()
                            .displayImage(imageView,
                                    new IImageLoader.LoadingBuilder()
                                            .setPlaceHolder(R.drawable.image_placeholder)
                                            .setUrl(mInstanceState.movie
                                                    .getScreenShots().get(position).getThumbnailUrl()));
                    collection.addView(imageView, 0);
                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup collection, int position, Object view) {
                    collection.removeView((ImageView) view);
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }
            });
        }
    }

    private static final class InstanceState implements Parcelable {
        Movie movie;

        InstanceState() {
        }

        InstanceState(Parcel in) {
            movie = in.readParcelable(Movie.class.getClassLoader());
        }

        public static final Creator<InstanceState> CREATOR = new Creator<MovieDetailsActivity.InstanceState>() {
            @Override
            public MovieDetailsActivity.InstanceState createFromParcel(Parcel in) {
                return new MovieDetailsActivity.InstanceState(in);
            }

            @Override
            public MovieDetailsActivity.InstanceState[] newArray(int size) {
                return new MovieDetailsActivity.InstanceState[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(movie, i);
        }
    }
}
