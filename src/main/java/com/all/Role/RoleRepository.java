package com.all.Role;

import com.all.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value ="SELECT * FROM role where role= :role", nativeQuery = true)
    @Transactional
    Role findRoleByName(@Param("role") String role);

}
