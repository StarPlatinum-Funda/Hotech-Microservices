package com.github.hotech.inventory.interfaces.rest.resources;


public record InventoryResource(Long id, String productTitle, String brandName, String productDescription,
                                Integer Quantity, Integer rechargeLimit, Long providerId, Long warehouseId) {
}
