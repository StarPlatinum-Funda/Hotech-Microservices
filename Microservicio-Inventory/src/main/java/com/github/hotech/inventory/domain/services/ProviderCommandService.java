package com.github.hotech.inventory.domain.services;

import com.github.hotech.inventory.domain.model.aggregates.Provider;
import com.github.hotech.inventory.domain.model.commands.CreateProviderCommand;
import com.github.hotech.inventory.domain.model.commands.UpdateProviderCommand;

import java.util.Optional;

public interface ProviderCommandService {
    Optional<Provider> handle(CreateProviderCommand command);
    Optional<Provider> handle(UpdateProviderCommand command);
}
