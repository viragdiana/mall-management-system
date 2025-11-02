package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.repository.Repository;

public final class Validation {
    private Validation() {}

    public static <T> T requireExists(Repository<T> repo, String id, String what) {
        T entity = repo.findById(id);
        if (entity == null) {
            throw new IllegalArgumentException(what + " with ID '" + id + "' does not exist.");
        }
        return entity;
    }
}
