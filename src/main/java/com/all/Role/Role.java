package com.all.Role;


import com.all.Person.Person;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String role;

    @ManyToMany(targetEntity = Person.class, mappedBy = "roles", cascade = CascadeType.ALL)
    private List<Person> persons;

}
