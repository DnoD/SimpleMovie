package com.dnod.simplemovie.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dnod.simplemovie.R;
import com.dnod.simplemovie.data.Movie;
import com.dnod.simplemovie.databinding.ItemMovieBinding;
import com.dnod.simplemovie.service.IImageLoader;

import java.util.ArrayList;
import java.util.List;

public final class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.Holder> {

    public interface Listener {
        void onItemClick(Movie movie);
    }

    private final LayoutInflater mLayoutInflater;
    private final List<Movie> mData;
    private final IImageLoader mImageLoader;
    private Listener mListener;

    public MoviesAdapter(Context context, IImageLoader imageLoader) {
        mLayoutInflater = LayoutInflater.from(context);
        mImageLoader = imageLoader;
        mData = new ArrayList<>();
        setHasStableIds(true);
    }

    public MoviesAdapter setListener(Listener listener) {
        this.mListener = listener;
        return this;
    }

    public void replaceAll(List<Movie> movies) {
        this.mData.clear();
        if (movies != null) {
            this.mData.addAll(movies);
            notifyDataSetChanged();
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder((ItemMovieBinding) DataBindingUtil.inflate(mLayoutInflater,
                R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Movie movie = mData.get(position);
        holder.bindingObject.setModel(movie);
        mImageLoader.displayImage(holder.bindingObject.cover,
                new IImageLoader.LoadingBuilder().setPlaceHolder(R.drawable.image_placeholder)
                        .setUrl(movie.getImages().getThumbnailUrl()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).getId().hashCode();
    }

    final class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemMovieBinding bindingObject;

        Holder(ItemMovieBinding binding) {
            super(binding.getRoot());
            bindingObject = binding;
            bindingObject.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(mData.get(getAdapterPosition()));
            }
        }
    }
}
