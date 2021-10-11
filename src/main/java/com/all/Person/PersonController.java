package com.all.Person;

import com.all.Role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    public List<String> getAllPerson() {
        return personService.findAll().stream()
                .map(Person::getNickname).collect(Collectors.toList());
    }

    @GetMapping("/{nickname}")
    public PersonResponse getByNickname(@PathVariable("nickname") String nickname) {
        return PersonResponse.fromPerson(personService.findPersonByNickname(nickname));
    }

    @PostMapping("/new_person")
    public void createPerson( @RequestParam("nickname") String nickname,
                              @RequestParam("password") String password,
                              @RequestParam("name") String name,
                              @RequestParam("roles") List<String> roles) {
        Person person = new Person();
        person.setNickname(nickname);
        person.setPassword(password);
        person.setName(name);
        List<Role> rolesList = new ArrayList<>();
        Role role;
        for (String i : roles) {
            role = personService.findRoleByName(i);
            rolesList.add(role);
        }
        person.setRoles(rolesList);
        personService.savePerson(person);
    }

    @PutMapping("/update_user/{nickname}")
    public void updatePerson(@PathVariable String nickname,
                             @RequestParam("password") String updatePassword,
                             @RequestParam("name") String updateName,
                             @RequestParam("roles") List<String> updateRoles
                             ) {
        Person person = personService.findPersonByNickname(nickname);
        person.setPassword(updatePassword);
        person.setName(updateName);
        List<Role> rolesList = new ArrayList<>();
        Role role;
        for (String i : updateRoles) {
            role = personService.findRoleByName(i);
            rolesList.add(role);
        }
        person.setRoles(rolesList);
        personService.savePerson(person);
    }

    @DeleteMapping("/delete_user/{nickname}")
    public void deletePerson(@PathVariable String nickname) {
        Person person = personService.findPersonByNickname(nickname);
        personService.deletePerson(person);
    }

}
