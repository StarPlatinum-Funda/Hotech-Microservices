package com.github.hotech.inventory.interfaces.rest.resources;

public record CreateInventoryResource(String productTitle, String brandName, String productDescription, Integer Quantity,
                                      Integer rechargeLimit, Long providerId, Long warehouseId) {
    public CreateInventoryResource {
        System.out.println(productTitle + productDescription + Quantity);

        if (productTitle == null || productTitle.isBlank())
            throw new IllegalArgumentException("The productTitle cannot be null or empty");
        else if (brandName == null || brandName.isBlank())
            throw new IllegalArgumentException("Brand's Name cannot be null or empty");
        else if (productDescription == null || productDescription.isBlank())
            throw new IllegalArgumentException("productDescription cannot be null or empty");
        else if (Quantity <= 0){
            throw new IllegalArgumentException("Starting quantity cannot be less than 1");
        }
        else if (rechargeLimit == null || rechargeLimit < 0) {
            throw new IllegalArgumentException("Recharge limit cannot be negative or empty");
        }
        else if (providerId == null) {
            throw new IllegalArgumentException("Provider's Id cannot be null");
        }
        else if (warehouseId == null) {
            throw new IllegalArgumentException("Warehouse's Id cannot be null");
        }
    }
}
