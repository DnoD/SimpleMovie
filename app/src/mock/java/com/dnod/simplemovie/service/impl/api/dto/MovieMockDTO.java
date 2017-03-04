package com.dnod.simplemovie.service.impl.api.dto;

import java.util.List;

public final class MovieMockDTO {
    private String id;
    private String title;
    private String description;
    private String starring;
    private String releaseDate;
    private String originalCoverUrl;
    private String thumbnailCoverUrl;
    private String genres;
    private List<ImagesMockDTO> screenShots;
    private float popularity;
    private int votesCount;
    private int duration;   //In minutes

    public int getDuration() {
        return duration;
    }

    public String getGenres() {
        return genres;
    }

    public List<ImagesMockDTO> getScreenShots() {
        return screenShots;
    }

    public String getStarring() {
        return starring;
    }

    public String getDescription() {
        return description;
    }

    public String getOriginalCoverUrl() {
        return originalCoverUrl;
    }

    public String getThumbnailCoverUrl() {
        return thumbnailCoverUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getVotesCount() {
        return votesCount;
    }
}
