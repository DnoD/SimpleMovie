package com.dnod.simplemovie.service;

import android.net.Uri;
import android.widget.ImageView;

public interface IImageLoader {
    int INVALID_RES_ID = 0;

    void displayImage(ImageView view, LoadingBuilder loadingBuilder);

    final class LoadingBuilder {
        String url;
        Uri uri;
        int placeHolder;

        public LoadingBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public LoadingBuilder setUri(Uri uri) {
            this.uri = uri;
            return this;
        }

        public LoadingBuilder setPlaceHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Uri getUri() {
            return uri;
        }

        public int getPlaceHolder() {
            return placeHolder;
        }

    }
}
