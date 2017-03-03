package com.dnod.simplemoview.service.impl.api;


import com.dnod.simplemoview.data.Images;
import com.dnod.simplemoview.data.Movie;
import com.dnod.simplemoview.service.Marshaller;
import com.dnod.simplemoview.service.impl.api.dto.MovieMockDTO;
import com.dnod.simplemoview.utils.TimeUtils;

import java.util.Date;

public final class MoviesMockMarshaller extends Marshaller<MovieMockDTO, Movie> {

    private static final MoviesMockMarshaller instance = new MoviesMockMarshaller();

    public static MoviesMockMarshaller getInstance() {
        return instance;
    }

    @Override
    public Movie fromEntity(MovieMockDTO entity) {
        return new Movie().setId(entity.getId())
                .setImages(new Images().setOriginalUrl(entity.getOriginalCoverUrl())
                        .setThumbnailUrl(entity.getThumbnailCoverUrl()))
                .setPopularity(entity.getPopularity())
                .setReleaseDate(new Date(TimeUtils.getEndpointDate(entity.getReleaseDate())))
                .setTitle(entity.getTitle())
                .setVotesCount(entity.getVotesCount());
    }

    @Override
    public MovieMockDTO toEntity(Movie entity) {
        return null;
    }
}
