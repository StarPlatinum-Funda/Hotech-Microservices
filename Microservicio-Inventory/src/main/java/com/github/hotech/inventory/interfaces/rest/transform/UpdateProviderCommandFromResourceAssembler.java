package com.github.hotech.inventory.interfaces.rest.transform;

import com.github.hotech.inventory.domain.model.commands.UpdateProviderCommand;
import com.github.hotech.inventory.interfaces.rest.resources.UpdateProviderResource;

public class UpdateProviderCommandFromResourceAssembler {
    public static UpdateProviderCommand toCommandFromResource(Long id, UpdateProviderResource resource){
        return new UpdateProviderCommand(id, resource.name(), resource.ruc(), resource.email());
    }
}
