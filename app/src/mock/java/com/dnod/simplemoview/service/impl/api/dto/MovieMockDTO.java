package com.dnod.simplemoview.service.impl.api.dto;

public final class MovieMockDTO {
    private String id;
    private String title;
    private String releaseDate;
    private String originalCoverUrl;
    private String thumbnailCoverUrl;
    private float popularity;
    private int votesCount;

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
