package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Mall;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryMallRepository
        extends InMemoryCrudRepository<Mall>
        implements MallRepository {

    @Override
    protected String getId(Mall entity) {
        return entity.getId();
    }
}
