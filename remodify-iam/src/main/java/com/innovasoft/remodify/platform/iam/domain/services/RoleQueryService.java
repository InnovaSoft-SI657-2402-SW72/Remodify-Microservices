package com.innovasoft.remodify.platform.iam.domain.services;

import com.innovasoft.remodify.platform.iam.domain.model.entities.Role;
import com.innovasoft.remodify.platform.iam.domain.model.queries.GetAllRolesQuery;
import com.innovasoft.remodify.platform.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
