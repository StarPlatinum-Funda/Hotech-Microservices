package com.github.hotech.profiles.domain.model.queries;


import com.github.hotech.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
