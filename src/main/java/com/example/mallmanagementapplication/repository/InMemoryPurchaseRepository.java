package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Purchase;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryPurchaseRepository
        extends InMemoryCrudRepository<Purchase>
        implements PurchaseRepository {

    @Override
    protected String getId(Purchase entity) {
        return entity.getId();
    }
}
