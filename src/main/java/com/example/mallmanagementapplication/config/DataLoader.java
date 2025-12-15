package com.example.mallmanagementapplication.config;

import com.example.mallmanagementapplication.model.*;
import com.example.mallmanagementapplication.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final MallRepository mallRepo;
    private final FloorRepository floorRepo;
    private final ShopRepository shopRepo;
    private final CustomerRepository customerRepo;
    private final PurchaseRepository purchaseRepo;
    private final SecurityStaffRepository securityRepo;
    private final MaintenanceStaffRepository maintenanceRepo;
    private final StaffAssignmentRepository assignmentRepo;
    private final MaintenanceTaskRepository taskRepo;
    private final ElectricalAssetRepository assetRepo;

    public DataLoader(
            MallRepository mallRepo,
            FloorRepository floorRepo,
            ShopRepository shopRepo,
            CustomerRepository customerRepo,
            PurchaseRepository purchaseRepo,
            SecurityStaffRepository securityRepo,
            MaintenanceStaffRepository maintenanceRepo,
            StaffAssignmentRepository assignmentRepo,
            MaintenanceTaskRepository taskRepo,
            ElectricalAssetRepository assetRepo
    ) {
        this.mallRepo = mallRepo;
        this.floorRepo = floorRepo;
        this.shopRepo = shopRepo;
        this.customerRepo = customerRepo;
        this.purchaseRepo = purchaseRepo;
        this.securityRepo = securityRepo;
        this.maintenanceRepo = maintenanceRepo;
        this.assignmentRepo = assignmentRepo;
        this.taskRepo = taskRepo;
        this.assetRepo = assetRepo;
    }

    @Override
    public void run(String... args) {

        /* ===================== MALLS ===================== */
        List<Mall> malls = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            malls.add(new Mall("Mall " + i, "City " + i, "Country " + i));
        }
        mallRepo.saveAll(malls);

        /* ===================== FLOORS ===================== */
        List<Floor> floors = new ArrayList<>();
        for (Mall mall : malls) {
            for (int level = 0; level < 3; level++) {
                floors.add(new Floor(level, mall));
            }
        }
        floorRepo.saveAll(floors);

        /* ===================== SHOPS ===================== */
        List<Shop> shops = new ArrayList<>();
        for (Floor floor : floors) {
            shops.add(new Shop(
                    "Shop L" + floor.getLevel(),
                    "Owner " + floor.getLevel(),
                    60,
                    ShopType.CLOTHING,
                    floor
            ));
        }
        shopRepo.saveAll(shops);

        /* ===================== CUSTOMERS ===================== */
        List<Customer> customers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            customers.add(new Customer(
                    "Customer " + i,
                    "EUR",
                    "customer" + i + "@mail.com"
            ));
        }
        customerRepo.saveAll(customers);

        /* ===================== SECURITY STAFF ===================== */
        List<SecurityStaff> guards = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            guards.add(new SecurityStaff("Guard " + i, "Badge-" + i));
        }
        securityRepo.saveAll(guards);

        /* ===================== MAINTENANCE STAFF ===================== */
        List<MaintenanceStaff> workers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            workers.add(new MaintenanceStaff(
                    "Worker " + i,
                    MaintenanceType.ELECTRICAL
            ));
        }
        maintenanceRepo.saveAll(workers);

        /* ===================== STAFF ASSIGNMENTS ===================== */
        List<StaffAssignment> assignments = new ArrayList<>();
        for (int i = 0; i < workers.size(); i++) {
            assignments.add(new StaffAssignment(
                    floors.get(i),
                    workers.get(i),
                    Shift.MORNING
            ));
        }
        assignmentRepo.saveAll(assignments);

        /* ===================== MAINTENANCE TASKS ===================== */
        List<MaintenanceTask> tasks = new ArrayList<>();
        for (StaffAssignment a : assignments) {
            tasks.add(new MaintenanceTask(
                    "Fix electrical issue on floor " + a.getFloor().getLevel(),
                    TaskStatus.PLANNED,
                    a
            ));
        }
        taskRepo.saveAll(tasks);

        /* ===================== ELECTRICAL ASSETS ===================== */
        List<ElectricalAsset> assets = new ArrayList<>();
        for (Floor f : floors) {
            assets.add(new ElectricalAsset(
                    f,
                    ElectricalType.LIGHT,
                    AssetStatus.WORKING
            ));
        }
        assetRepo.saveAll(assets);

        /* ===================== PURCHASES ===================== */
        List<Purchase> purchases = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            purchases.add(new Purchase(
                    BigDecimal.valueOf(25 + i),
                    customers.get(i % customers.size()),
                    shops.get(i % shops.size())
            ));
        }
        purchaseRepo.saveAll(purchases);

        System.out.println("✅ DataLoader finished: demo data inserted successfully.");
    }
}
