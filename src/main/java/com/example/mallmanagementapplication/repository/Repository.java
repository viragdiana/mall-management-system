package com.example.mallmanagementapplication.repository;

import java.util.List;

public interface Repository<T> {
    void save(T entity);            // CREATE sau UPDATE
    T findById(String id);          // READ by ID
    List<T> findAll();              // READ all
    void delete(String id);         // DELETE
}