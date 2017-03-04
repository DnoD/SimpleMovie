package com.dnod.simplemovie.service.impl;

import android.widget.ImageView;

import com.dnod.simplemovie.service.IImageLoader;


public class DefaultImageLoaderImpl implements IImageLoader {
    @Override
    public void displayImage(ImageView view, LoadingBuilder loadingBuilder) {
        //TODO: Add implementation
        view.setImageResource(loadingBuilder.getPlaceHolder());
    }
}
