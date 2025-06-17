package com.github.hotech.inventory.domain.model.aggregates;

import com.github.hotech.inventory.domain.model.commands.CreateItemsCommand;
import com.github.hotech.inventory.domain.model.commands.UpdateInventoryCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Entity
public class Inventory extends AbstractAggregateRoot<Inventory> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productTitle;

    @Column(nullable = false)
    private String brandName;

    @Column(nullable = false)
    private String productDescription;

    @Column(nullable = false)
    private Integer productQuantity;

    @Column(nullable = false)
    private Integer rechargeLimit;

    @Column(nullable = false)
    private Long providerId;

    @Column(nullable = false)
    private Long warehouseId;


    protected Inventory() {}


    public Inventory(CreateItemsCommand command) {
        this.productTitle = command.itemTitle();
        this.brandName = command.brandName();
        this.productDescription = command.itemDescription();
        this.productQuantity = command.itemQuantity();
        this.rechargeLimit = command.rechargeLimit();
        this.providerId = command.providerId();
        this.warehouseId = command.warehouseId();
    }

    public Inventory updateInformation(UpdateInventoryCommand command) {
        this.productTitle = command.itemTitle();
        this.brandName = command.brandName();
        this.productDescription = command.itemDescription();
        this.productQuantity = command.itemQuantity();
        this.rechargeLimit = command.rechargeLimit();
        this.providerId = command.providerId();
        this.warehouseId = command.warehouseId();

        return this;
    }
}
