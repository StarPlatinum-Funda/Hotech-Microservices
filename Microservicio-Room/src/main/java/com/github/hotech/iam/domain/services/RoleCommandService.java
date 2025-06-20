package com.github.hotech.iam.domain.services;


import com.github.hotech.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
