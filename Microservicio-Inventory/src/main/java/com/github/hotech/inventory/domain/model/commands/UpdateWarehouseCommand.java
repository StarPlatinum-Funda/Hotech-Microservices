package com.github.hotech.inventory.domain.model.commands;

public record UpdateWarehouseCommand(Long id, Long userId) {
    public UpdateWarehouseCommand{
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id must be greater than 0");
        }

        if(userId == null){
            throw new IllegalArgumentException("userId can't be null");
        }
    }
}
