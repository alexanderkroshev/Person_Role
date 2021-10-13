package com.all.Person;

import com.all.Role.Role;
import com.all.Role.RoleRepository;
import com.google.common.util.concurrent.Service;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;


import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
class PersonAndRoleServiceTest {


    @Autowired
    protected RoleRepository roleRepository;
    @Autowired
    protected PersonRepository personRepository;

    @Before
    public void setUp() {
        Role role = new Role();
        role.setId(10L);
        role.setRole("admin");

        List<Role> roleList = new ArrayList<>();
        roleList.add(role);

        Person person = new Person();
        String nickname = "Vlad_95";
        person.setNickname(nickname);
        person.setName("vlad");
        person.setPassword("pass3D");
        person.setRoles(roleList);
        given(this.roleRepository.findRoleByName(role.getRole())).willReturn(role);
        given(this.personRepository.findPersonByNickname(nickname)).willReturn(person);
        given(this.personRepository.save(person)).willReturn(person);
    }

    @Test
    void findPersonByNickname() {
        String nickname = "Vlad_95";
        Person person = new Person();
        person.setNickname(nickname);
        person.setName("vlad");
        person.setPassword("pass3Dee");
        this.personRepository.save(person);
        Person person2 = this.personRepository.findPersonByNickname(nickname);
        assertThat(person2.getNickname()).isEqualTo(nickname);
    }

    @Test
    void findRoleByName() {
        String name = "admin";
        Role role = new Role();
        role.setId(10L);
        role.setRole(name);
        roleRepository.save(role);

        Role role1 = roleRepository.findRoleByName(name);
        assertThat(role1.getRole()).isEqualTo(name);
    }

    @Test
    void savePerson() {
        String nickname = "Vlad_95";
        Person person = new Person();
        person.setNickname(nickname);
        person.setName("vlad");
        person.setPassword("pass3D");
        personRepository.save(person);

        Person person2 = personRepository.findPersonByNickname(nickname);
        assertThat(personRepository.save(person2)).isEqualTo(person);
    }

    @Test
    void deletePerson() {
        String nickname = "Vlad_95";
        Person person = new Person();
        person.setNickname(nickname);
        person.setName("vlad");
        person.setPassword("pass3Dee");
        this.personRepository.save(person);
        this.personRepository.delete(person);
        assertThat(this.personRepository.findPersonByNickname(nickname)).isNull();
    }
}