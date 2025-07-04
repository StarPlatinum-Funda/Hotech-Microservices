package com.github.hotechbackend.iam.domain.services;

import com.github.hotechbackend.iam.domain.model.entities.Role;
import com.github.hotechbackend.iam.domain.model.queries.GetAllRolesQuery;
import com.github.hotechbackend.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
