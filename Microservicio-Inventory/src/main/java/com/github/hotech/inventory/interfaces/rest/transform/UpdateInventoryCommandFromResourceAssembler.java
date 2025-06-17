package com.github.hotech.inventory.interfaces.rest.transform;

import com.github.hotech.inventory.domain.model.commands.UpdateInventoryCommand;
import com.github.hotech.inventory.interfaces.rest.resources.UpdateInventoryResource;

public class UpdateInventoryCommandFromResourceAssembler {
    public static UpdateInventoryCommand toCommandFromResource(Long inventoryId, UpdateInventoryResource resource){
        return new UpdateInventoryCommand(inventoryId, resource.productTitle(), resource.brandName(), resource.productDescription(),
                resource.Quantity(), resource.rechargeLimit(), resource.providerId(), resource.warehouseId());
    }
}
