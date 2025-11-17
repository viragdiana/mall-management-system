package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Identifiable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public abstract class InFileRepository<T extends Identifiable> implements Repository<T> {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file;
    private final Class<T> type;
    private final Map<String, T> store = new LinkedHashMap<>();

    protected InFileRepository(String filePath, Class<T> type) {
        this.file = new File(filePath);
        this.type = type;
        initFileIfMissing();
        loadFromFile();
    }

    // -------------------- CRUD METHODS ---------------------

    @Override
    public synchronized void save(T entity) {
        validateId(entity);
        store.put(entity.getId(), entity);
        writeToFile();
    }

    @Override
    public synchronized T findById(String id) {
        return store.get(id);
    }

    @Override
    public synchronized List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public synchronized void delete(String id) {
        store.remove(id);
        writeToFile();
    }

    // -------------------- INTERNAL HELPERS ---------------------

    private void validateId(T entity) {
        if (entity.getId() == null || entity.getId().isBlank()) {
            throw new IllegalArgumentException("Entity ID cannot be null or empty");
        }
    }

    private void initFileIfMissing() {
        try {
            File folder = file.getParentFile();
            if (folder != null && !folder.exists()) {
                folder.mkdirs();
            }
            if (!file.exists()) {
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, List.of());
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize JSON file at " + file.getAbsolutePath(), e);
        }
    }

    private void loadFromFile() {
        try {
            if (file.length() == 0) return;

            CollectionType listType =
                    mapper.getTypeFactory().constructCollectionType(List.class, type);

            List<T> items = mapper.readValue(file, listType);
            store.clear();

            for (T item : items) {
                if (item != null && item.getId() != null) {
                    store.put(item.getId(), item);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not load JSON data from " + file.getAbsolutePath(), e);
        }
    }

    private void writeToFile() {
        try {
            List<T> items = new ArrayList<>(store.values());
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, items);
        } catch (IOException e) {
            throw new RuntimeException("Could not write JSON data to " + file.getAbsolutePath(), e);
        }
    }
}
