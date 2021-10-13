package com.all.Person;

import com.all.Role.Role;
import com.all.Role.RoleRepository;
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
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
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

    @Before
    void setUp(){
     //  дописать события до
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
        Person person = service.findPersonByNickname("Vlad_9532");
        this.service.deletePerson(person);
        assertThat(this.service.findPersonByNickname("Vlad_9532")).isNull();
    }
}