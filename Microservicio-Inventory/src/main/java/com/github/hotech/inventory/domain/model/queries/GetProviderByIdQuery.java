package com.github.hotech.inventory.domain.model.queries;

public record GetProviderByIdQuery(Long ProviderId){
    public GetProviderByIdQuery{
        if(ProviderId == null || ProviderId == 0)
            throw new IllegalArgumentException("Id cannot be null or zero");
    }
}
