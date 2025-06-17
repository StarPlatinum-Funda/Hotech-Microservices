package com.github.hotech.inventory.application.queryservices;

import com.github.hotech.inventory.domain.model.aggregates.Provider;
import com.github.hotech.inventory.domain.model.queries.GetAllProvidersQuery;
import com.github.hotech.inventory.domain.model.queries.GetProviderByIdQuery;
import com.github.hotech.inventory.domain.services.ProviderQueryService;
import com.github.hotech.inventory.infrastructure.persistence.jpa.repositories.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderQueryServiceImpl implements ProviderQueryService {

    private final ProviderRepository providerRepository;

    public ProviderQueryServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public List<Provider> handle(GetAllProvidersQuery query) {
        return providerRepository.findAll();
    }

    @Override
    public Optional<Provider> handle(GetProviderByIdQuery query) {
        return providerRepository.findById(query.ProviderId());
    }
}
