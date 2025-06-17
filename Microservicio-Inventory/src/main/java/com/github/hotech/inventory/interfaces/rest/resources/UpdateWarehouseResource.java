package com.github.hotech.inventory.interfaces.rest.resources;

public record UpdateWarehouseResource(Long userId) {
    public UpdateWarehouseResource{
        if(userId == null){
            throw new IllegalArgumentException("userId can't be null");
        }
    }
}
