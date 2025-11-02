package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Floor;
import com.example.mallmanagementapplication.repository.FloorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FloorService {
    private final FloorRepository floorRepo;

    public FloorService(FloorRepository floorRepo) {
        this.floorRepo = floorRepo;
    }

    public void addFloor(Floor floor) {
        floorRepo.save(floor);
    }

    public Floor getFloor(String id) {
        return floorRepo.findById(id);
    }

    public List<Floor> getAllFloors() {
        return floorRepo.findAll();
    }

    public void deleteFloor(String id) {
        floorRepo.delete(id);
    }

}
