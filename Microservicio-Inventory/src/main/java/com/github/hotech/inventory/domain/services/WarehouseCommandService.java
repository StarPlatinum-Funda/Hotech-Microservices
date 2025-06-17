package com.github.hotech.inventory.domain.services;

import com.github.hotech.inventory.domain.model.aggregates.Warehouse;
import com.github.hotech.inventory.domain.model.commands.CreateWarehouseCommand;
import com.github.hotech.inventory.domain.model.commands.UpdateWarehouseCommand;

import java.util.Optional;

public interface WarehouseCommandService {
    Optional<Warehouse> handle(CreateWarehouseCommand command);
    Optional<Warehouse> handle(UpdateWarehouseCommand command);
}
