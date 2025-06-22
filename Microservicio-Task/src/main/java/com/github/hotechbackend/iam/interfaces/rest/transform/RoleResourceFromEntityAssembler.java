package com.github.hotechbackend.iam.interfaces.rest.transform;

import com.github.hotechbackend.iam.domain.model.entities.Role;
import com.github.hotechbackend.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
