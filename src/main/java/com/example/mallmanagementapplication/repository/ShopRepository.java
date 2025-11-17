package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class ShopRepository extends InFileRepository<Shop> {

    public ShopRepository() {
        super("src/main/resources/data/shops.json", Shop.class);
    }
}
