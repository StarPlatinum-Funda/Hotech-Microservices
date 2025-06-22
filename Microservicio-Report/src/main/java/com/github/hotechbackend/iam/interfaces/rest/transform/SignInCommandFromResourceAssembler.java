package com.github.hotechbackend.iam.interfaces.rest.transform;

import com.github.hotechbackend.iam.domain.model.commands.SignInCommand;
import com.github.hotechbackend.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
