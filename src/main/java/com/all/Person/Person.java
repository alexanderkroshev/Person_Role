package com.all.Person;

import com.all.Role.Role;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Person {

    @Id
    @Column(unique = true)
    @NotNull
    private String nickname;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL)
    private List<Role> roles;


}
