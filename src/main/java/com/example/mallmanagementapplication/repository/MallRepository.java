package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Mall;
import org.springframework.stereotype.Repository;

@Repository
public class MallRepository extends InFileRepository<Mall> {

    public MallRepository() {
        super("src/main/resources/data/malls.json", Mall.class);
    }
}
