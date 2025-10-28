package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryShopRepository
        extends InMemoryCrudRepository<Shop>
        implements ShopRepository {

    @Override
    protected String getId(Shop entity) {
        return entity.getId();
    }
}
