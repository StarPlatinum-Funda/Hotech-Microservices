package com.github.hotech.inventory.domain.model.commands;

public record CreateItemsCommand(String itemTitle, String brandName, String itemDescription, Integer itemQuantity, Integer rechargeLimit,
                                 Long providerId, Long warehouseId) {
    public CreateItemsCommand{
        if (itemTitle == null || itemTitle.isBlank()) {
            throw new IllegalArgumentException("Item's Title cannot be null or empty");
        }
        if (brandName == null || brandName.isBlank()) {
           throw new IllegalArgumentException("Brand's Name cannot be null or empty");
        }
        if (itemDescription == null || itemDescription.isBlank()) {
            throw new IllegalArgumentException("Item's Description cannot be null or empty");
        }
        if (itemQuantity == null || itemQuantity < 0) {
            throw new IllegalArgumentException("Item's quantity cannot be negative or empty");
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
