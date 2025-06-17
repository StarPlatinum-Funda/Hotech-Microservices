package com.github.hotech.inventory.domain.services;

import com.github.hotech.inventory.domain.model.aggregates.Provider;
import com.github.hotech.inventory.domain.model.queries.GetAllProvidersQuery;
import com.github.hotech.inventory.domain.model.queries.GetProviderByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProviderQueryService {
    List<Provider> handle(GetAllProvidersQuery query);
    Optional<Provider> handle(GetProviderByIdQuery query);
}
