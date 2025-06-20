package com.github.hotech.backend.iam.domain.services;

import com.github.hotech.backend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
