package com.github.hotech.inventory.interfaces.rest;

import com.github.hotech.inventory.domain.model.aggregates.Warehouse;
import com.github.hotech.inventory.domain.model.commands.CreateWarehouseCommand;
import com.github.hotech.inventory.domain.model.queries.GetAllWarehouseQuery;
import com.github.hotech.inventory.domain.model.queries.GetWarehouseByIdQuery;
import com.github.hotech.inventory.domain.services.WarehouseCommandService;
import com.github.hotech.inventory.domain.services.WarehouseQueryService;
import com.github.hotech.inventory.interfaces.rest.resources.CreateWarehouseResource;
import com.github.hotech.inventory.interfaces.rest.resources.UpdateWarehouseResource;
import com.github.hotech.inventory.interfaces.rest.resources.WarehouseResource;
import com.github.hotech.inventory.interfaces.rest.transform.UpdateWarehouseCommandFromResourceAssembler;
import com.github.hotech.inventory.interfaces.rest.transform.WarehouseResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/v1/warehouse", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Warehouse", description = "Warehouse Management Endpoints")
public class WarehouseController {

    private final WarehouseQueryService warehouseQueryService;
    private final WarehouseCommandService warehouseCommandService;

    public WarehouseController(WarehouseQueryService warehouseQueryService, WarehouseCommandService warehouseCommandService) {
        this.warehouseQueryService = warehouseQueryService;
        this.warehouseCommandService = warehouseCommandService;
    }

    @Operation(summary = "Creates a new warehouse with an user id of 0")
    @PostMapping
    public ResponseEntity<WarehouseResource> createWarehouse(
            @RequestBody CreateWarehouseResource createWarehouseResource) {
        Optional<Warehouse> WarehouseSource = warehouseCommandService.handle(new CreateWarehouseCommand());

        return WarehouseSource.map(source -> new ResponseEntity<>(WarehouseResourceFromEntityAssembler.
                        toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Recovers all warehouses in the database")
    @GetMapping
    public ResponseEntity<List<WarehouseResource>> getAllWarehouses() {
        List<Warehouse> warehouseSource = warehouseQueryService.handle(new GetAllWarehouseQuery());
        var warehouseResource = warehouseSource
                .stream().map(WarehouseResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(warehouseResource);
    }

    @Operation(summary = "Recovers a warehouse with the given idh")
    @GetMapping("/{warehouseId}")
    public ResponseEntity<WarehouseResource> getWarehouseById(@PathVariable Long warehouseId) {
        var GetWarehouseByIdQuery = new GetWarehouseByIdQuery(warehouseId);
        var Warehouse = warehouseQueryService.handle(GetWarehouseByIdQuery);
        if (Warehouse.isEmpty()) return ResponseEntity.notFound().build();
        var warehouseResource = WarehouseResourceFromEntityAssembler.toResourceFromEntity(Warehouse.get());
        return ResponseEntity.ok(warehouseResource);
    }

    @Operation(summary = "updates a warehouse data")
    @PutMapping("/{warehouseId}")
    public ResponseEntity<WarehouseResource> updateWarehouse(@PathVariable Long warehouseId, @RequestBody UpdateWarehouseResource resource){
        var updateWarehouseCommand = UpdateWarehouseCommandFromResourceAssembler.toCommandFromResource(warehouseId, resource);
        var updatedWarehouse = warehouseCommandService.handle(updateWarehouseCommand);
        if (updatedWarehouse.isEmpty()) return ResponseEntity.notFound().build();

        var warehouseResource = WarehouseResourceFromEntityAssembler.toResourceFromEntity(updatedWarehouse.get());
        return ResponseEntity.ok(warehouseResource);
    }
}