package com.all.Role;

import lombok.Data;

@Data
public class RoleResponse {

    private final String name;
    private final Long id;


    public static RoleResponse fromRole(Role role) {
        return new RoleResponse(role.getRole(), role.getId());
    }
}
