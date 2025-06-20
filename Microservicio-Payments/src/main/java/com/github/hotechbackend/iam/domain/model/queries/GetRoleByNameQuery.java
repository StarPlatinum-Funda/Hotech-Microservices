package com.github.hotechbackend.iam.domain.model.queries;

import com.github.hotechbackend.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles roleName) {
}
