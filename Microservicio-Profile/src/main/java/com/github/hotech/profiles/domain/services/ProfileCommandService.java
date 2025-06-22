package com.github.hotech.profiles.domain.services;


import com.github.hotech.profiles.domain.model.aggregates.Profile;
import com.github.hotech.profiles.domain.model.commands.CreateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
}
