package com.all.Person;

import com.all.Role.Role;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
public class Person {

    @Id
    @Column(unique = true, nullable = false)
    @NotEmpty
    private String nickname;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL)
    private List<Role> roles;


}
