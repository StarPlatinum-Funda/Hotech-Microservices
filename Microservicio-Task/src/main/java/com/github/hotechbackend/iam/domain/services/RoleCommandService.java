package com.github.hotechbackend.iam.domain.services;

import com.github.hotechbackend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
