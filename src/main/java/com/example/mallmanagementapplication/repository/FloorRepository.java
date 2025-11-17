package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Floor;
import org.springframework.stereotype.Repository;

@Repository
public class FloorRepository extends InFileRepository<Floor> {

    public FloorRepository() {
        super("src/main/resources/data/floors.json", Floor.class);
    }
}
