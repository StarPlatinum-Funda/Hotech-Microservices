package com.github.hotech.iam.domain.model.queries;


import com.github.hotech.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery (Roles roleName) {
}
