package com.all.Person;

import com.all.Role.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.*;



import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonAndRoleService service;

    private static final Long TEST_Role_ID = 1L;
    private static final String TEST_nickname_ID = "Vlad_95";


    @BeforeEach
    void setUp() {
        Role role = new Role();
        Person person = new Person();
        List<Role> roleList = new ArrayList<>();
        role.setId(TEST_Role_ID);
        role.setRole("admin");
        roleList.add(role);

        person.setName("Vladislav");
        person.setNickname("Vlad_95");
        person.setPassword("password");
        person.setRoles(roleList);
        given(this.service.findRoleByName(role.getRole())).willReturn(role);
        given(this.service.findPersonByNickname(person.getNickname())).willReturn(person);
        doNothing().when(this.service).savePerson(person);
    }

    @Test
    void createPerson() throws Exception {
        mockMvc.perform(post("/persons/new_person").param("nickname", "Bob").
                param("name", "Bobislav").param("password", "3Pdfdfs").
                param("roles", "admin")).
                andExpect(status().isOk());
        verify(service, times(1)).findRoleByName(any());
        verify(service, times(1)).savePerson(any());
        verifyNoMoreInteractions(service);
    }

    @Test
    void updatePerson() throws Exception {
        mockMvc.perform(put("/persons/update_person/{nickname}", TEST_nickname_ID).
                param("name", "Vladislav").param("password", "3Pdfdfs").
                param("roles", "admin")).
                andExpect(status().isOk());
        verify(service, times(1)).findRoleByName(any());
        verify(service, times(1)).findPersonByNickname(any());
    }


    @Test
    void deletePerson() throws Exception {
        mockMvc.perform(delete("/persons/delete_person/{nickname}", TEST_nickname_ID)).
                andExpect(status().isOk());
        verify(service, times(1)).findPersonByNickname(any());
        verify(service, times(1)).deletePerson(any());
        verifyNoMoreInteractions(service);
    }
}

