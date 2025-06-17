package com.github.hotech.iam.interfaces.rest.transform;


import com.github.hotech.iam.domain.model.entities.Role;
import com.github.hotech.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
