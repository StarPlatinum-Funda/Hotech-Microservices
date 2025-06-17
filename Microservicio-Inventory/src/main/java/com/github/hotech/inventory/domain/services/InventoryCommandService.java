package com.github.hotech.inventory.domain.services;

import com.github.hotech.inventory.domain.model.aggregates.Inventory;
import com.github.hotech.inventory.domain.model.commands.*;

import java.util.Optional;

public interface InventoryCommandService {
    Optional<Inventory> handle(CreateItemsCommand command);
    Optional<Inventory> handle(UpdateInventoryCommand command);
    void handle(DeleteItemsCommand command);
}