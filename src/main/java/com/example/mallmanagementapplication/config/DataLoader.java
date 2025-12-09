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

    private final MallRepository malls;
    private final FloorRepository floors;
    private final ShopRepository shops;
    private final CustomerRepository customers;
    private final PurchaseRepository purchases;
    private final SecurityStaffRepository securityRepo;
    private final MaintenanceStaffRepository maintenanceRepo;
    private final StaffAssignmentRepository assignments;
    private final MaintenanceTaskRepository tasks;
    private final ElectricalAssetRepository assets;

    public DataLoader(
            MallRepository malls,
            FloorRepository floors,
            ShopRepository shops,
            CustomerRepository customers,
            PurchaseRepository purchases,
            SecurityStaffRepository securityRepo,
            MaintenanceStaffRepository maintenanceRepo,
            StaffAssignmentRepository assignments,
            MaintenanceTaskRepository tasks,
            ElectricalAssetRepository assets
    ) {
        this.malls = malls;
        this.floors = floors;
        this.shops = shops;
        this.customers = customers;
        this.purchases = purchases;
        this.securityRepo = securityRepo;
        this.maintenanceRepo = maintenanceRepo;
        this.assignments = assignments;
        this.tasks = tasks;
        this.assets = assets;
    }

    @Override
    public void run(String... args) throws Exception {

        // ==== MALLS ====
        List<Mall> mallList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            mallList.add(new Mall("Mall " + i, "City " + i, "Country " + i));
        }
        malls.saveAll(mallList);


        // ==== FLOORS ====
        List<Floor> floorList = new ArrayList<>();
        for (Mall m : mallList) {
            for (int i = 0; i < 10; i++) {
                floorList.add(new Floor(i, m));
            }
        }
        floors.saveAll(floorList);


        // ==== SHOPS ====
        List<Shop> shopList = new ArrayList<>();
        for (Floor f : floorList) {
            for (int i = 0; i < 10; i++) {
                shopList.add(new Shop(
                        "Shop" + i,
                        "Owner " + i,
                        50 + i,
                        ShopType.CLOTHING,
                        f
                ));
            }
        }
        shops.saveAll(shopList);


        // ==== CUSTOMERS ====
        List<Customer> customerList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            customerList.add(new Customer("Customer " + i, "EUR", "customer" + i + "@mail.com"));
        }
        customers.saveAll(customerList);


        // ==== SECURITY STAFF ====
        List<SecurityStaff> secList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            secList.add(new SecurityStaff("Guard " + i, "B-" + i));
        }
        securityRepo.saveAll(secList);


        // ==== MAINTENANCE STAFF ====
        List<MaintenanceStaff> maintList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            maintList.add(new MaintenanceStaff("Worker " + i, MaintenanceType.ELECTRICAL));
        }
        maintenanceRepo.saveAll(maintList);


        // ==== STAFF ASSIGNMENTS ====
        List<StaffAssignment> assignList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            assignList.add(new StaffAssignment(
                    floorList.get(i),
                    maintList.get(i),
                    Shift.MORNING
            ));
        }
        assignments.saveAll(assignList);


        // ==== MAINTENANCE TASKS ====
        List<MaintenanceTask> taskList = new ArrayList<>();
        for (StaffAssignment a : assignList) {

            MaintenanceTask t = new MaintenanceTask(
                    "Fix issue on floor " + a.getFloor().getLevel(),
                    TaskStatus.PLANNED,
                    a.getFloor()   // <-- 🔥 legăm task-ul de floor
            );

            t.setAssignment(a); // poate exista sau nu, dar e logic să existe

            taskList.add(t);
        }
        tasks.saveAll(taskList);



        // ==== ELECTRICAL ASSETS ====
        List<ElectricalAsset> assetList = new ArrayList<>();
        for (Floor f : floorList) {
            assetList.add(new ElectricalAsset(f, ElectricalType.LIGHT, AssetStatus.WORKING));
        }
        assets.saveAll(assetList);


        // ==== PURCHASES ====
        List<Purchase> purchaseList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            purchaseList.add(new Purchase(
                    BigDecimal.valueOf(20 + i),
                    customerList.get(i % 10),
                    shopList.get(i)
            ));
        }
        purchases.saveAll(purchaseList);

        System.out.println("=== 10 entries per entity inserted successfully ===");
    }
}
