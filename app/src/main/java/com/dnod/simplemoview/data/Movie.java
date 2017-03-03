package com.dnod.simplemoview.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public final class Movie implements Parcelable {
    private String id;
    private String mTitle;
    private Date mReleaseDate;
    private float mPopularity;
    private int mVotesCount;
    private Images mImages;

    public Movie() {}

    public String getId() {
        return id;
    }

    public Movie setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public Movie setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public Date getReleaseDate() {
        return mReleaseDate;
    }

    public Movie setReleaseDate(Date releaseDate) {
        this.mReleaseDate = releaseDate;
        return this;
    }

    public float getPopularity() {
        return mPopularity;
    }

    public Movie setPopularity(float popularity) {
        this.mPopularity = popularity;
        return this;
    }

    public int getVotesCount() {
        return mVotesCount;
    }

    public Movie setVotesCount(int votesCount) {
        this.mVotesCount = votesCount;
        return this;
    }

    public Images getImages() {
        return mImages;
    }

    public Movie setImages(Images images) {
        this.mImages = images;
        return this;
    }

    Movie(Parcel in) {
        id = in.readString();
        mTitle = in.readString();
        mReleaseDate = (Date) in.readSerializable();
        mPopularity = in.readFloat();
        mVotesCount = in.readInt();
        mImages = in.readParcelable(Images.class.getClassLoader());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(mTitle);
        parcel.writeSerializable(mReleaseDate);
        parcel.writeFloat(mPopularity);
        parcel.writeInt(mVotesCount);
        parcel.writeParcelable(mImages, i);
    }
}
