package com.all.Role;

import com.all.Person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


}
