package com.github.hotech.profiles.interfaces.rest.resources;

public record CreateProfileResource(
        String firstName,
        String lastName,
        String phoneNumber,
        String email
        ) { }
