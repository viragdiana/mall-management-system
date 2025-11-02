package com.example.mallmanagementapplication.service;

import com.example.mallmanagementapplication.model.Floor;
import com.example.mallmanagementapplication.model.MaintenanceTask;
import com.example.mallmanagementapplication.model.StaffAssignment;
import com.example.mallmanagementapplication.model.TaskStatus;
import com.example.mallmanagementapplication.repository.FloorRepository;
import com.example.mallmanagementapplication.repository.MaintenanceTaskRepository;
import com.example.mallmanagementapplication.repository.StaffAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mallmanagementapplication.service.Validation.requireExists;
@Service
public class MaintenanceTaskService {
    private final MaintenanceTaskRepository taskRepo;
    private final StaffAssignmentRepository assignmentRepo;
    private final FloorRepository floorRepo;

    public MaintenanceTaskService(MaintenanceTaskRepository taskRepo,
                                  StaffAssignmentRepository assignmentRepo,
                                  FloorRepository floorRepo) {
        this.taskRepo = taskRepo;
        this.assignmentRepo = assignmentRepo;
        this.floorRepo = floorRepo;
    }

    /** Creează un task fără a-l atașa la un anumit etaj (doar salvare + validare assignmentId opțional). */
    public void addTask(MaintenanceTask task) {
        if (task.getAssignmentId() != null) {
            requireExists(assignmentRepo, task.getAssignmentId(), "StaffAssignment");
        }
        taskRepo.save(task);
    }

    /**
     * Atașează task-ul la un etaj anume (menține lista Floor.tasks).
     * Dacă task are assignmentId, verifică faptul că acel assignment aparține aceluiași floor.
     */
    public void addTaskToFloor(String floorId, MaintenanceTask task) {
        Floor floor = requireExists(floorRepo, floorId, "Floor");

        if (task.getAssignmentId() != null) {
            StaffAssignment sa = requireExists(assignmentRepo, task.getAssignmentId(), "StaffAssignment");
            if (!sa.getFloorId().equals(floorId)) {
                throw new IllegalArgumentException(
                        "Task.assignmentId belongs to a different floor (expected " + floorId + ", got " + sa.getFloorId() + ")."
                );
            }
        }

        taskRepo.save(task);

        if (floor.getTasks().stream().noneMatch(t -> t.getId().equals(task.getId()))) {
            floor.addTask(task);
        }
    }

    public MaintenanceTask getTask(String id) {
        return taskRepo.findById(id);
    }

    public List<MaintenanceTask> getAllTasks() {
        return taskRepo.findAll();
    }

    public void deleteTask(String id) {
        taskRepo.delete(id);
        // opțional: elimină și din Floor.tasks
    }

    public List<MaintenanceTask> getTasksByStatus(TaskStatus status) {
        return taskRepo.findByStatus(status);
    }
}
