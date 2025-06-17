package com.github.hotech.inventory.domain.services;

import com.github.hotech.inventory.domain.model.aggregates.Inventory;
import com.github.hotech.inventory.domain.model.aggregates.Provider;
import com.github.hotech.inventory.domain.model.aggregates.Warehouse;
import com.github.hotech.inventory.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface InventoryQueryService {
    List<Inventory> handle(GetAllItemsQuery query);
    Optional<Inventory> handle(GetItemByIdQuery query);
    List<Inventory> handle(GetItemByBrandQuery query);
}
