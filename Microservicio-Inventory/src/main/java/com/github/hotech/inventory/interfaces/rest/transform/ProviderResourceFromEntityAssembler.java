package com.github.hotech.inventory.interfaces.rest.transform;

import com.github.hotech.inventory.domain.model.aggregates.Provider;
import com.github.hotech.inventory.domain.model.commands.CreateWarehouseCommand;
import com.github.hotech.inventory.interfaces.rest.resources.ProviderResource;

public class ProviderResourceFromEntityAssembler {
    public static ProviderResource toResourceFromEntity(Provider entity) {
        return new ProviderResource(entity.getId(),entity.getName(), entity.getRuc(), entity.getEmail());
    }
}
