package com.github.hotech.inventory.interfaces.rest.resources;

public record UpdateInventoryResource(String productTitle, String brandName, String productDescription, Integer Quantity,
                                      Integer rechargeLimit, Long providerId, Long warehouseId) {
    public UpdateInventoryResource {
        if (productTitle == null || productTitle.isBlank())
            throw new IllegalArgumentException("Title cannot be null or empty");
        if (productDescription == null || productDescription.isBlank())
            throw new IllegalArgumentException("Product description cannot be null or empty");
        if (Quantity < 0) {
            throw new IllegalArgumentException("Starting quantity cannot be less than 0");
        }
        if (rechargeLimit == null || rechargeLimit < 0) {
            throw new IllegalArgumentException("Recharge limit cannot be negative or empty");
        }
        if (providerId == null) {
            throw new IllegalArgumentException("Provider's Id cannot be null");
        }
        if (warehouseId == null) {
            throw new IllegalArgumentException("Warehouse's Id cannot be null");
        }
    }
}
