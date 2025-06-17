package com.github.hotech.inventory.infrastructure.listeners;

import com.github.hotech.inventory.domain.model.aggregates.Inventory;
import com.github.hotech.inventory.domain.model.commands.CreateItemsCommand;
import com.github.hotech.inventory.infrastructure.persistence.jpa.repositories.ItemRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InventoryListener {

    private final ItemRepository itemRepository;

    public InventoryListener(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private Map<String, Object> item;

    /*
    {
        "id": 1,
        "productTitle": "",
        "brandName": "",
        "productDescription": "",
        "productQuantity": 1,
        "rechargeLimit": 0,
        "providerId": 1,
        "warehouseId": 1
    }
    */
    @KafkaListener(topics = "item-topic", groupId = "inventory", containerFactory = "kafkaListenerContainerFactory")
    public void reciveItem(Map<String, Object> val){
        item = val;
        var id = (Number)item.get("id");
        System.out.println(item.toString());

        if(itemRepository.existsById(id.longValue())){
            throw new IllegalArgumentException("This item title already exists");
        }

        System.out.println("Se puede crear");

        CreateItemsCommand command = new CreateItemsCommand(
                (String)item.get("productTitle"),
                (String)item.get("brandName"),
                (String)item.get("productDescription"),
                ((Number)item.get("productQuantity")).intValue(),
                ((Number)item.get("rechargeLimit")).intValue(),
                ((Number)item.get("providerId")).longValue(),
                ((Number)item.get("warehouseId")).longValue()
        );
        var item = new Inventory(command);
        try{
            var createdItem = itemRepository.save(item);
            System.out.println("Item succesfuly created: " + createdItem.toString());
        } catch(Exception e){
            throw new IllegalArgumentException("Error creating item: " + e.getMessage());
        }
    }
}
