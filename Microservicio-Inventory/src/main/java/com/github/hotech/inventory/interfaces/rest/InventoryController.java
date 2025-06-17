package com.github.hotech.inventory.interfaces.rest;


import com.github.hotech.inventory.domain.model.aggregates.Inventory;
import com.github.hotech.inventory.domain.model.commands.DeleteItemsCommand;
import com.github.hotech.inventory.domain.model.queries.GetAllItemsQuery;
import com.github.hotech.inventory.domain.model.queries.GetItemByBrandQuery;
import com.github.hotech.inventory.domain.model.queries.GetItemByIdQuery;
import com.github.hotech.inventory.domain.services.InventoryCommandService;
import com.github.hotech.inventory.domain.services.InventoryQueryService;
import com.github.hotech.inventory.interfaces.rest.resources.CreateInventoryResource;
import com.github.hotech.inventory.interfaces.rest.resources.InventoryResource;
import com.github.hotech.inventory.interfaces.rest.resources.UpdateInventoryResource;
import com.github.hotech.inventory.interfaces.rest.transform.CreateInventoryCommandFromResourceAssembler;
import com.github.hotech.inventory.interfaces.rest.transform.InventoryResourceFromEntityAssembler;
import com.github.hotech.inventory.interfaces.rest.transform.UpdateInventoryCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/v1/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Inventory", description = "Inventory Management Endpoints")
public class InventoryController {

    private final InventoryQueryService inventoryQueryService;
    private final InventoryCommandService inventoryCommandService;

    public InventoryController(InventoryQueryService inventoryQueryService, InventoryCommandService inventoryCommandService) {
        this.inventoryQueryService = inventoryQueryService;
        this.inventoryCommandService = inventoryCommandService;
    }

    @Operation(summary = "Create new item im the inventory")
    @PostMapping
    public ResponseEntity<InventoryResource> createItem(
            @RequestBody CreateInventoryResource CreateInventoryResource) {
        Optional<Inventory> InventorySource = inventoryCommandService
                .handle(CreateInventoryCommandFromResourceAssembler.toCommandFromResource(CreateInventoryResource));
        return InventorySource.map(source ->
                        new ResponseEntity<>(InventoryResourceFromEntityAssembler
                                .toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Recovers all items in the inventory")
    @GetMapping
    public ResponseEntity<List<InventoryResource>> getAllItems() {
        List<Inventory> inventorySource = inventoryQueryService.handle(new GetAllItemsQuery());
        var inventoryResource = inventorySource
                .stream().map(InventoryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(inventoryResource);
    }

    @Operation(summary = "Recovers all items that belong to the given brand")
    @GetMapping("/brand")
    public ResponseEntity<?> getInventoryByBrand(@RequestParam String brandName) {
        if (brandName == null || brandName.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Inventory> inventorySource = inventoryQueryService.handle(new GetItemByBrandQuery(brandName));
        var inventoryResource = inventorySource
                .stream().map(InventoryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(inventoryResource);
    }

    @Operation(summary = "Recovers an item with the id given in the path")
    @GetMapping("/{inventoryId}")
    public ResponseEntity<InventoryResource> getInventoryById(@PathVariable Long inventoryId) {
        var GetItemsByIdQuery = new GetItemByIdQuery(inventoryId);
        var Inventory = inventoryQueryService.handle(GetItemsByIdQuery);
        if (Inventory.isEmpty()) return ResponseEntity.notFound().build();
        var inventoryResource = InventoryResourceFromEntityAssembler.toResourceFromEntity(Inventory.get());
        return ResponseEntity.ok(inventoryResource);

    }

    @Operation(summary = "Updates an item in the inventory")
    @PutMapping("/{inventoryId}")
    public ResponseEntity<InventoryResource> updateInventory(@PathVariable Long inventoryId, @RequestBody UpdateInventoryResource updateInventoryResource) {
        var UpdateInventoryCommand = UpdateInventoryCommandFromResourceAssembler.toCommandFromResource(inventoryId, updateInventoryResource);
        var updatedInventory = inventoryCommandService.handle(UpdateInventoryCommand);
        if (updatedInventory.isEmpty()) return ResponseEntity.notFound().build();

        var inventoryResource = InventoryResourceFromEntityAssembler.toResourceFromEntity(updatedInventory.get());
        return ResponseEntity.ok(inventoryResource);
    }

    @Operation(summary = "Deletes the item with the id given in the path")
    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long inventoryId) {
        var deleteItemsCommand = new DeleteItemsCommand(inventoryId);
        inventoryCommandService.handle(deleteItemsCommand);
        return ResponseEntity.ok("Course with id " + inventoryId + " has been deleted.");
    }
}

