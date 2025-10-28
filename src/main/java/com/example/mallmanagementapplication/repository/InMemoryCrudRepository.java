package com.example.mallmanagementapplication.repository;

import java.util.*;

public abstract class InMemoryCrudRepository<T> implements CrudRepository<T> {

    protected final Map<String, T> storage = new HashMap<>();

    @Override
    public void save(T entity) {
        storage.put(getId(entity), entity);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    /**
     * Each subclass tells how to extract the unique ID from its entity.
     */
    protected abstract String getId(T entity);
}
