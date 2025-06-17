package com.github.hotech.inventory.domain.model.aggregates;

import com.github.hotech.inventory.domain.model.commands.CreateProviderCommand;
import com.github.hotech.inventory.domain.model.commands.UpdateProviderCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Entity
public class Provider extends AbstractAggregateRoot<Provider> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String ruc;

    @Column(nullable = false)
    private String email;

    protected Provider() {}

    public Provider(CreateProviderCommand command){
        this.name = command.name();
        this.ruc = command.ruc();
        this.email = command.email();
    }

    public Provider updateProvider(UpdateProviderCommand command){
        this.name = command.name();
        this.ruc = command.ruc();
        this.email = command.email();

        return this;
    }
}
