package com.github.hotech.inventory.interfaces.rest;

import com.github.hotech.inventory.domain.model.aggregates.Inventory;
import com.github.hotech.inventory.domain.model.aggregates.Provider;
import com.github.hotech.inventory.domain.model.queries.GetAllProvidersQuery;
import com.github.hotech.inventory.domain.model.queries.GetProviderByIdQuery;
import com.github.hotech.inventory.domain.services.ProviderCommandService;
import com.github.hotech.inventory.domain.services.ProviderQueryService;
import com.github.hotech.inventory.interfaces.rest.resources.*;
import com.github.hotech.inventory.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/v1/provider", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Providers", description = "Providers Management Endpoints")
public class ProviderController {

    private final ProviderCommandService  providerCommandService;
    private final ProviderQueryService providerQueryService;

    public ProviderController(ProviderCommandService providerCommandService, ProviderQueryService providerQueryService) {
        this.providerCommandService = providerCommandService;
        this.providerQueryService = providerQueryService;
    }

    @Operation(summary = "Create a new provider for the items")
    @PostMapping
    public ResponseEntity<ProviderResource> createProvider(
            @RequestBody CreateProviderResource createProviderResource) {
        Optional<Provider> providerSource = providerCommandService
                .handle(CreateProviderCommandFromResourceAssembler.toCommandFromResource(createProviderResource));
        return providerSource.map(source ->
                        new ResponseEntity<>(ProviderResourceFromEntityAssembler
                                .toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Gets lists of all providers")
    @GetMapping
    public ResponseEntity<List<ProviderResource>> getAllProviders() {
        List<Provider> providerSource = providerQueryService.handle(new GetAllProvidersQuery());
        var providerResource = providerSource
                .stream().map(ProviderResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(providerResource);
    }

    @Operation(summary = "Get provider by id in the path")
    @GetMapping("/{providerId}")
    public ResponseEntity<ProviderResource> getProviderById(@PathVariable Long providerId){
        var query = new GetProviderByIdQuery(providerId);
        var provider =  providerQueryService.handle(query);
        if(provider.isEmpty()) return ResponseEntity.notFound().build();
        var resource = ProviderResourceFromEntityAssembler.toResourceFromEntity(provider.get());
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{providerId}")
    public ResponseEntity<ProviderResource> updateProvider(@PathVariable Long providerId, @RequestBody UpdateProviderResource resource){
        var command = UpdateProviderCommandFromResourceAssembler.toCommandFromResource(providerId, resource);
        var provider =  providerCommandService.handle(command);
        if(provider.isEmpty()) return ResponseEntity.notFound().build();
        var providerResource = ProviderResourceFromEntityAssembler.toResourceFromEntity(provider.get());
        return ResponseEntity.ok(providerResource);
    }
}
