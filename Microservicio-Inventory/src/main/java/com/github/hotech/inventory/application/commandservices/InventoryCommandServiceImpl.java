package com.github.hotech.inventory.application.commandservices;

import com.github.hotech.inventory.domain.model.aggregates.Inventory;
import com.github.hotech.inventory.domain.model.commands.CreateItemsCommand;
import com.github.hotech.inventory.domain.model.commands.DeleteItemsCommand;
import com.github.hotech.inventory.domain.model.commands.UpdateInventoryCommand;
import com.github.hotech.inventory.domain.services.InventoryCommandService;
import com.github.hotech.inventory.infrastructure.persistence.jpa.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryCommandServiceImpl implements InventoryCommandService {

    private final ItemRepository itemRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public InventoryCommandServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Optional<Inventory> handle(CreateItemsCommand command) {
        if(itemRepository.existsByProductTitle(command.itemTitle())){
            throw new IllegalArgumentException("This item title already exists");
        }

        if(command.itemQuantity() < 0){
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }

        if(command.rechargeLimit() < 0){
            throw new IllegalArgumentException("Recharge limit cannot be less than 0");
        }

        var item = new Inventory(command);
        var createdItems = itemRepository.save(item);
        return Optional.of(createdItems);
    }

    @Override
    public Optional<Inventory> handle(UpdateInventoryCommand command)
    {
        if(itemRepository.existsByProductTitleAndIdNot((command.itemTitle()), command.id())){
            throw new IllegalArgumentException("This item title already exists");
        }

        if(command.itemQuantity() < 0){
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }

        if(command.rechargeLimit() < 0){
            throw new IllegalArgumentException("Recharge limit cannot be less than 0");
        }

        var result = itemRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Item does not exist");
        var inventoryToUpdate = result.get();
        try {
            var updatedInventory = itemRepository.save(inventoryToUpdate.updateInformation(command));
            return Optional.of(updatedInventory);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating Inventory: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteItemsCommand command) {
        if (!itemRepository.existsById(command.Id())) {
            throw new IllegalArgumentException("Course does not exist");
        }
        try {
            itemRepository.deleteById(command.Id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting course: " + e.getMessage());
        }

    }
}