package com.github.hotech.inventory.domain.model.commands;

public record CreateProviderCommand(String name, String ruc, String email) {
    public CreateProviderCommand {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (ruc == null || ruc.isBlank()){
            throw new IllegalArgumentException("RUC cannot be empty");
        }

        if (email == null || email.isBlank()){
            throw new IllegalArgumentException("Email cannot be empty");
        }
    }
}
