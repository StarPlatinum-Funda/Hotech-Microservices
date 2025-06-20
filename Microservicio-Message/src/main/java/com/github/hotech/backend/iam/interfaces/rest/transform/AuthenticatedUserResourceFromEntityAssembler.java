package com.github.hotech.backend.iam.interfaces.rest.transform;

import com.github.hotech.backend.iam.domain.model.aggregates.User;
import com.github.hotech.backend.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), user.getSerializedRoles(), token);
    }
}
