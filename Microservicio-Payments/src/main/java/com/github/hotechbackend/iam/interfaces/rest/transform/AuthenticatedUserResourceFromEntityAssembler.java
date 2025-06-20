package com.github.hotechbackend.iam.interfaces.rest.transform;

import com.github.hotechbackend.iam.domain.model.aggregates.User;
import com.github.hotechbackend.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), user.getSerializedRoles(), token);
    }
}
