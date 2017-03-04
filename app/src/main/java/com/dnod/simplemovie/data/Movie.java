package com.dnod.simplemovie.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Movie implements Parcelable {
    private String id;
    private String mTitle;
    private String mDescription;
    private String mStarring;
    private String mGenres;
    private long mReleaseDate;
    private float mPopularity;
    private int mVotesCount;
    private int mDuration;      //In minutes
    private Images mImages;
    private List<Images> mScreenShots = new ArrayList<>();

    public Movie() {}

    public int getDuration() {
        return mDuration;
    }

    public Movie setDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public String getGenres() {
        return mGenres;
    }

    public Movie setGenres(String genres) {
        this.mGenres = genres;
        return this;
    }

    public List<Images> getScreenShots() {
        return mScreenShots;
    }

    public Movie setScreenShots(List<Images> screenShots) {
        this.mScreenShots = screenShots;
        return this;
    }

    public String getStarring() {
        return mStarring;
    }

    public Movie setStarring(String starring) {
        this.mStarring = starring;
        return this;
    }

    public String getDescription() {
        return mDescription;
    }

    public Movie setDescription(String description) {
        this.mDescription = description;
        return this;
    }

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

    public long getReleaseDate() {
        return mReleaseDate;
    }

    public Movie setReleaseDate(long releaseDate) {
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
        mDescription = in.readString();
        mStarring = in.readString();
        mGenres = in.readString();
        mReleaseDate = in.readLong();
        mPopularity = in.readFloat();
        mVotesCount = in.readInt();
        mDuration = in.readInt();
        mImages = in.readParcelable(Images.class.getClassLoader());
        mScreenShots = new ArrayList<>();
        in.readTypedList(mScreenShots, Images.CREATOR);
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
        parcel.writeString(mDescription);
        parcel.writeString(mStarring);
        parcel.writeString(mGenres);
        parcel.writeLong(mReleaseDate);
        parcel.writeFloat(mPopularity);
        parcel.writeInt(mVotesCount);
        parcel.writeInt(mDuration);
        parcel.writeParcelable(mImages, i);
        parcel.writeTypedList(mScreenShots);
    }
}
