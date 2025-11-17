package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Floor;
import com.example.mallmanagementapplication.repository.FloorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mallmanagementapplication.service.Validation.requireExists;

@Service
public class FloorService {

    private final FloorRepository floorRepo;

    public FloorService(FloorRepository floorRepo) {
        this.floorRepo = floorRepo;
    }

    public void addFloor(Floor floor) {
        boolean exists = floorRepo.findAll().stream()
                .anyMatch(f -> f.getNumber() == floor.getNumber());

        if (exists) {
            throw new IllegalArgumentException("A floor with number " + floor.getNumber() + " already exists.");
        }

        floorRepo.save(floor);
    }

    public Floor getFloor(String id) {
        return requireExists(floorRepo, id, "Floor");
    }

    public List<Floor> getAllFloors() {
        return floorRepo.findAll();
    }

    public void deleteFloor(String id) {
        requireExists(floorRepo, id, "Floor");
        floorRepo.delete(id);
    }
}
