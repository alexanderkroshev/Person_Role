package com.all.Person;

import com.all.Role.Role;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "nickname")
public class Person {
    @Id
    @Column(unique = true)
    private String nickname;
    @JsonIgnore
    private String password;

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL)
    private List<Role> roles;
}
//orphanRemoval = true, cascade = CascadeType.REMOVE