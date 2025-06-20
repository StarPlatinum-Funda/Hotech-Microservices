package com.github.hotech.backend.iam.domain.model.queries;

import com.github.hotech.backend.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles roleName) {
}
