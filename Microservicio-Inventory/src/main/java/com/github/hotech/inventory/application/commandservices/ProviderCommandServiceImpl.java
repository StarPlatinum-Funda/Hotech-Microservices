package com.github.hotech.inventory.application.commandservices;

import com.github.hotech.inventory.domain.model.aggregates.Provider;
import com.github.hotech.inventory.domain.model.commands.CreateProviderCommand;
import com.github.hotech.inventory.domain.model.commands.UpdateProviderCommand;
import com.github.hotech.inventory.domain.services.ProviderCommandService;
import com.github.hotech.inventory.infrastructure.persistence.jpa.repositories.ItemRepository;
import com.github.hotech.inventory.infrastructure.persistence.jpa.repositories.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderCommandServiceImpl implements ProviderCommandService {

    private final ProviderRepository providerRepository;

    public ProviderCommandServiceImpl(ProviderRepository providerRepository, ItemRepository itemRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public Optional<Provider> handle(CreateProviderCommand command) {
        if(providerRepository.existsByRuc(command.ruc())) {
            throw new IllegalArgumentException("This ruc already exists");
        }

        var provider = new Provider(command);
        var createdProvider = providerRepository.save(provider);
        return Optional.of(createdProvider);
    }

    @Override
    public Optional<Provider> handle(UpdateProviderCommand command) {

        if(providerRepository.existsByRucAndIdNot(command.ruc(), command.id())) {
            throw new IllegalArgumentException("This ruc already exists");
        }

        var result = providerRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Provider does not exist");
        var providerToUpdate = result.get();
        try{
            var updatedProvider = providerRepository.save(providerToUpdate.updateProvider(command));
            return Optional.of(updatedProvider);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating Provider: " + e.getMessage());
        }
    }
}
