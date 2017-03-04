package com.dnod.simplemovie.service.impl.api;


import android.text.TextUtils;

import com.dnod.simplemovie.data.Images;
import com.dnod.simplemovie.data.Movie;
import com.dnod.simplemovie.service.Marshaller;
import com.dnod.simplemovie.service.impl.api.dto.ImagesMockDTO;
import com.dnod.simplemovie.service.impl.api.dto.MovieMockDTO;
import com.dnod.simplemovie.utils.TimeUtils;

final class ImagesMockMarshaller extends Marshaller<ImagesMockDTO, Images> {

    private static final ImagesMockMarshaller instance = new ImagesMockMarshaller();

    static ImagesMockMarshaller getInstance() {
        return instance;
    }

    @Override
    public Images fromEntity(ImagesMockDTO entity) {
        return new Images().setOriginalUrl(entity.getOriginalUrl())
                .setThumbnailUrl(entity.getThumbnailUrl());
    }

    @Override
    public ImagesMockDTO toEntity(Images entity) {
        return null;
    }
}
