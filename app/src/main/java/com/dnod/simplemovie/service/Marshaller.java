package com.dnod.simplemovie.service;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Marshaller<E, D> {

    public Set<D> fromEntities(@NonNull Set<? extends E> entities){
        Set<D> res = new HashSet<>();
        for(E entity : entities)
            res.add(fromEntity(entity));
        return res;
    }

    public List<D> fromEntities(@NonNull List<? extends E> entities){
        List<D> res = new ArrayList<D>();
        for(E entity : entities)
            res.add(fromEntity(entity));
        return res;
    }

    public abstract D fromEntity(E entity);

    public List<E> toEntities(@NonNull List<? extends D> entities){
        List<E> res = new ArrayList<E>();
        for(D entity : entities)
            res.add(toEntity(entity));
        return res;
    }

    public Set<E> toEntities(@NonNull Set<? extends D> entities){
        Set<E> res = new HashSet<>();
        for(D entity : entities)
            res.add(toEntity(entity));
        return res;
    }

    public abstract E toEntity(D entity);
}
