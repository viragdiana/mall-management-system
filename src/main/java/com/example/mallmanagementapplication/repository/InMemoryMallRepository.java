package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Mall;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class InMemoryMallRepository implements MallRepository{
    private final Map<String, Mall> malls = new HashMap<>();
    @Override
    public void save(Mall mall) {
        malls.put(mall.getId(), mall);
    }

    @Override
    public List<Mall> findAll() {
        return new ArrayList<>(malls.values());
    }

    @Override
    public Optional<Mall> findById(String id) {
        return Optional.ofNullable(malls.get(id));
    }

    @Override
    public void delete(String id) {
        malls.remove(id);

    }
}
