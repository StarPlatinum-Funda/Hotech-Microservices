package com.github.hotech.inventory.domain.model.queries;

public record GetWarehouseByIdQuery(Long warehouseId) {
    public GetWarehouseByIdQuery {
        if(warehouseId == null || warehouseId == 0)
            throw new IllegalArgumentException("Id cannot be null or zero");
    }
}
