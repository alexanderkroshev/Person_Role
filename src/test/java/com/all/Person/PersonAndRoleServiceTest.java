package com.all.Person;

import com.all.Role.Role;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@SpringBootTest
class PersonAndRoleServiceTest {


    @Autowired
    protected PersonAndRoleService service;

    @Test
    void findPersonByNickname() {
        String nickname = "Vlad_95";
        Person person2 = this.service.findPersonByNickname(nickname);
        assertThat(person2.getNickname()).isEqualTo(nickname);
    }

    @Test
    void findRoleByName() {
        String name = "admin";
        Role role1 = service.findRoleByName(name);
        assertThat(role1.getRole()).isEqualTo(name);
    }

    @Test
    void savePerson() {
        Person person = new Person();
        String nickname = "Vlad_95hgjgj";
        person.setNickname(nickname);
        person.setName("vlad");
        person.setPassword("pass3D");


        service.savePerson(person);
        Person person2 = service.findPersonByNickname(nickname);
        assertThat(person2.getNickname()).isEqualTo(nickname);
    }

    @Test
    void deletePerson() {
        Person person = service.findPersonByNickname("Vlad_95hgjgj");
        this.service.deletePerson(person);
        assertThat(this.service.findPersonByNickname("Vlad_95hgjgj")).isNull();
    }
}