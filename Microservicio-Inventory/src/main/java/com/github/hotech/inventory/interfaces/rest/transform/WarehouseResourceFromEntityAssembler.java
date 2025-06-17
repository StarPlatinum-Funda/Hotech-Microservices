package com.github.hotech.inventory.interfaces.rest.transform;

import com.github.hotech.inventory.domain.model.aggregates.Warehouse;
import com.github.hotech.inventory.interfaces.rest.resources.WarehouseResource;

public class WarehouseResourceFromEntityAssembler {
    public static WarehouseResource toResourceFromEntity(Warehouse entity) {
        return new WarehouseResource(entity.getId(), entity.getUserId());
    }
}
