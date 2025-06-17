package com.github.hotech.inventory.interfaces.rest.transform;

import com.github.hotech.inventory.domain.model.commands.CreateProviderCommand;
import com.github.hotech.inventory.interfaces.rest.resources.CreateProviderResource;

public class CreateProviderCommandFromResourceAssembler {
    public static CreateProviderCommand toCommandFromResource(CreateProviderResource resource){
        return new CreateProviderCommand(resource.name(), resource.ruc(), resource.email());
    }
}
