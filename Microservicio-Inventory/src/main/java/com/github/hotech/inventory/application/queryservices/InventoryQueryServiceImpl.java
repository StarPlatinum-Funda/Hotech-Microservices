package com.github.hotech.inventory.application.queryservices;


import com.github.hotech.inventory.domain.model.aggregates.Inventory;
import com.github.hotech.inventory.domain.model.queries.*;
import com.github.hotech.inventory.domain.services.InventoryQueryService;
import com.github.hotech.inventory.infrastructure.persistence.jpa.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryQueryServiceImpl implements InventoryQueryService {
    private final ItemRepository itemRepository;

    public InventoryQueryServiceImpl(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    @Override
    public List<Inventory> handle(GetAllItemsQuery query){
        return itemRepository.findAll();
    }

    @Override
    public Optional<Inventory> handle(GetItemByIdQuery query){
        return itemRepository.findById(query.itemId());
    }

    @Override
    public List<Inventory> handle(GetItemByBrandQuery query)
    {
        return itemRepository.findAllByBrandName(query.brandName());
    }



}
