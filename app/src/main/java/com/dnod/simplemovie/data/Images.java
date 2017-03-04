package com.dnod.simplemovie.data;


import android.os.Parcel;
import android.os.Parcelable;

public final class Images implements Parcelable {
    private String mOriginalUrl;
    private String mThumbnailUrl;

    public Images() {}

    public String getOriginalUrl() {
        return mOriginalUrl;
    }

    public Images setOriginalUrl(String originalUrl) {
        this.mOriginalUrl = originalUrl;
        return this;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public Images setThumbnailUrl(String thumbnailUrl) {
        this.mThumbnailUrl = thumbnailUrl;
        return this;
    }

    Images(Parcel in) {
        mOriginalUrl = in.readString();
        mThumbnailUrl = in.readString();
    }

    public static final Creator<Images> CREATOR = new Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel in) {
            return new Images(in);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mOriginalUrl);
        parcel.writeString(mThumbnailUrl);
    }
}
