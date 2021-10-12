package com.all.Person;

import com.all.Role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonAndRoleService personAndRoleService;

    @Autowired
    public PersonController(PersonAndRoleService personAndRoleService) {
        this.personAndRoleService = personAndRoleService;
    }

    @GetMapping("/all")
    public List<String> getAllPerson() {
        return personAndRoleService.findAll().stream()
                .map(Person::getNickname).collect(Collectors.toList());
    }

    @GetMapping("/{nickname}")
    public PersonResponse getByNickname(@PathVariable("nickname") String nickname) {
        return PersonResponse.fromPerson(personAndRoleService.findPersonByNickname(nickname));
    }

    @PostMapping("/new_person")
    public void createPerson(@RequestParam("nickname") String nickname,
                              @RequestParam("password") String password,
                              @RequestParam("name") String name,
                              @RequestParam("roles") List<String> roles) {
  //      personAndRoleService.
        Person person = new Person();
        person.setNickname(nickname);
        person.setPassword(password);
        person.setName(name);
        List<Role> rolesList = new ArrayList<>();
        Role role;
        for (String i : roles) {
            role = personAndRoleService.findRoleByName(i);
            rolesList.add(role);
        }
        person.setRoles(rolesList);
        personAndRoleService.savePerson(person);
    }

    @PutMapping("/update_person/{nickname}")
    public void updatePerson(@PathVariable String nickname,
                             @RequestParam("password") String updatePassword,
                             @RequestParam("name") String updateName,
                             @RequestParam("roles") List<String> updateRoles
                             ) {
        Person person = personAndRoleService.findPersonByNickname(nickname);
        person.setPassword(updatePassword);
        person.setName(updateName);
        List<Role> rolesList = new ArrayList<>();
        Role role;
        for (String i : updateRoles) {
            role = personAndRoleService.findRoleByName(i);
            rolesList.add(role);
        }
        person.setRoles(rolesList);
        personAndRoleService.savePerson(person);
    }

    @DeleteMapping("/delete_person/{nickname}")
    public void deletePerson(@PathVariable String nickname) {
        Person person = personAndRoleService.findPersonByNickname(nickname);
        personAndRoleService.deletePerson(person);
    }

}
