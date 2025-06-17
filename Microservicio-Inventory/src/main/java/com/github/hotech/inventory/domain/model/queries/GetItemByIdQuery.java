package com.github.hotech.inventory.domain.model.queries;

public record GetItemByIdQuery(Long itemId) {
    public GetItemByIdQuery {
        if (itemId == null || itemId == 0)
            throw new IllegalArgumentException("Id cannot be null or zero");
    }
}
