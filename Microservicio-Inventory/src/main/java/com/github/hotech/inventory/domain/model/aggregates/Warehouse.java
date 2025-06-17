package com.github.hotech.inventory.domain.model.aggregates;

import com.github.hotech.inventory.domain.model.commands.CreateWarehouseCommand;
import com.github.hotech.inventory.domain.model.commands.UpdateWarehouseCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Entity
public class Warehouse extends AbstractAggregateRoot<Warehouse> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    protected Warehouse() {

    }

    public Warehouse(CreateWarehouseCommand command) {
        userId = 0L;
    }

    public Warehouse updateWarehouse(UpdateWarehouseCommand command){
        this.userId = command.userId();
        return this;
    }
}
