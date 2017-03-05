package com.dnod.simplemovie.service.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.dnod.simplemovie.service.IImageLoader;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;

/**
 * The cache and Bitmap loading logic was taken here: https://developer.android.com/topic/performance/graphics/cache-bitmap.html
 */
public class DefaultImageLoaderImpl implements IImageLoader {

    private final LruCache<String, Bitmap> mMemoryCache;
    private final Object mPauseWorkLock = new Object();
    private boolean mPauseWork = false;
    private boolean mExitTasksEarly = false;

    public DefaultImageLoaderImpl() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    @Override
    public void displayImage(ImageView view, LoadingBuilder loadingBuilder) {
        view.setImageResource(loadingBuilder.getPlaceHolder());
        if (loadingBuilder.getUrl() != null)
            loadBitmap(loadingBuilder.getUrl(), view);
    }

    private void loadBitmap(String url, ImageView imageView) {
        final Bitmap bitmap = getBitmapFromMemCache(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            BitmapWorkerTask task = new BitmapWorkerTask(url, imageView);
            task.execute();
        }
    }

    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    private Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    /**
     * The actual AsyncTask that will asynchronously process the image.
     */
    private class BitmapWorkerTask extends AsyncTask<Void, Void, Bitmap> {
        private String mUrl;
        private final WeakReference<ImageView> imageViewReference;

        BitmapWorkerTask(String url, ImageView imageView) {
            mUrl = url;
            imageViewReference = new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            Bitmap bitmap = null;
            // Wait here if work is paused and the task is not cancelled
            synchronized (mPauseWorkLock) {
                while (mPauseWork && !isCancelled()) {
                    try {
                        mPauseWorkLock.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
            // If the image cache is available and this task has not been cancelled by another
            // thread and the ImageView that was originally bound to this task is still bound back
            // to this task and our "exit early" flag is not set then try and fetch the bitmap from
            // the cache
            if (!isCancelled() && imageViewReference.get() != null
                    && !mExitTasksEarly) {
                bitmap = getBitmapFromMemCache(mUrl);
            }

            // If the bitmap was not found in the cache and this task has not been cancelled by
            // another thread and the ImageView that was originally bound to this task is still
            // bound back to this task and our "exit early" flag is not set, then call the main
            // process method (as implemented by a subclass)
            if (bitmap == null && !isCancelled() && imageViewReference.get() != null
                    && !mExitTasksEarly) {
                try {
                    URL url = new URL(mUrl);
                    bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bitmap != null) {
                addBitmapToMemoryCache(mUrl, bitmap);
            }

            return bitmap;
        }

        /**
         * Once the image is processed, associates it to the imageView
         */
        @Override
        protected void onPostExecute(Bitmap value) {
            // if cancel was called on this task or the "exit early" flag is set then we're done
            if (isCancelled() || mExitTasksEarly) {
                value = null;
            }

            final ImageView imageView = imageViewReference.get();
            if (value != null && imageView != null) {
                imageView.setImageBitmap(value);
            }
        }

        @Override
        protected void onCancelled(Bitmap value) {
            super.onCancelled(value);
            synchronized (mPauseWorkLock) {
                mPauseWorkLock.notifyAll();
            }
        }
    }

}
