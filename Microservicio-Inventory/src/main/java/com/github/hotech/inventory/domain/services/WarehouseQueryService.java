package com.github.hotech.inventory.domain.services;

import com.github.hotech.inventory.domain.model.aggregates.Warehouse;
import com.github.hotech.inventory.domain.model.queries.GetAllWarehouseQuery;
import com.github.hotech.inventory.domain.model.queries.GetWarehouseByIdQuery;

import java.util.List;
import java.util.Optional;

public interface WarehouseQueryService {
    List<Warehouse> handle(GetAllWarehouseQuery query);
    Optional<Warehouse> handle(GetWarehouseByIdQuery query);
}
