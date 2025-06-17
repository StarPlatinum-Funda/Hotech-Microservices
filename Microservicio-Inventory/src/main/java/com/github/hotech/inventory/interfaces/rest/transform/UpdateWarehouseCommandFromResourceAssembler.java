package com.github.hotech.inventory.interfaces.rest.transform;

import com.github.hotech.inventory.domain.model.commands.UpdateWarehouseCommand;
import com.github.hotech.inventory.interfaces.rest.resources.UpdateWarehouseResource;

public class UpdateWarehouseCommandFromResourceAssembler {
    public static UpdateWarehouseCommand toCommandFromResource(Long warehouseId,UpdateWarehouseResource resource){
        return new UpdateWarehouseCommand(warehouseId, resource.userId());
    }
}
