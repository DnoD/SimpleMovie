package com.dnod.simplemovie.service.impl.api;


import android.text.TextUtils;

import com.dnod.simplemovie.data.Images;
import com.dnod.simplemovie.data.Movie;
import com.dnod.simplemovie.service.Marshaller;
import com.dnod.simplemovie.service.impl.api.dto.MovieMockDTO;
import com.dnod.simplemovie.utils.TimeUtils;

import java.util.Date;

final class MoviesMockMarshaller extends Marshaller<MovieMockDTO, Movie> {

    private static final MoviesMockMarshaller instance = new MoviesMockMarshaller();

    static MoviesMockMarshaller getInstance() {
        return instance;
    }

    @Override
    public Movie fromEntity(MovieMockDTO entity) {
        return new Movie().setId(entity.getId())
                .setImages(new Images().setOriginalUrl(entity.getOriginalCoverUrl())
                        .setThumbnailUrl(entity.getThumbnailCoverUrl()))
                .setPopularity(entity.getPopularity())
                .setReleaseDate(TextUtils.isEmpty(entity.getReleaseDate()) ?
                        0 : TimeUtils.getEndpointDate(entity.getReleaseDate()))
                .setTitle(entity.getTitle())
                .setStarring(entity.getStarring())
                .setDescription(entity.getDescription())
                .setVotesCount(entity.getVotesCount());
    }

    @Override
    public MovieMockDTO toEntity(Movie entity) {
        return null;
    }
}
