package com.github.hotech.inventory.interfaces.rest.transform;

import com.github.hotech.inventory.domain.model.aggregates.Inventory;
import com.github.hotech.inventory.interfaces.rest.resources.InventoryResource;

public class InventoryResourceFromEntityAssembler {
    public  static InventoryResource toResourceFromEntity(Inventory entity){
        return new InventoryResource(entity.getId(),entity.getProductTitle(), entity.getBrandName(), entity.getProductDescription(),
                entity.getProductQuantity(), entity.getRechargeLimit(), entity.getProviderId(), entity.getWarehouseId());
    }
}
