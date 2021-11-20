package com.all.Person;

import com.all.Role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
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
                             @RequestParam("roles") List<Long> listOfRoles) {
        Person person = new Person();
        person.setNickname(nickname);
        person.setPassword(password);
        person.setName(name);

        List<Role> rolesList = new ArrayList<>();
        Role role;
        for (Long i : listOfRoles) {
            role = personAndRoleService.findRoleById(i);
            rolesList.add(role);
        }
        person.setRoles(rolesList);
        personAndRoleService.savePerson(person);
    }

    @PutMapping("/update_person/{id}")
    public void updatePerson(@PathVariable Long id,
                             @RequestParam("password") String updatePassword,
                             @RequestParam("name") String updateName,
                             @RequestParam("roles") List<Long> updateRoles) {
        Person person = personAndRoleService.findPersonById(id);
        person.setPassword(updatePassword);
        person.setName(updateName);

        List<Role> rolesList = new ArrayList<>();
        Role role;
        for (Long i : updateRoles) {
            role = personAndRoleService.findRoleById(i);
            rolesList.add(role);
        }
        person.setRoles(rolesList);
        personAndRoleService.savePerson(person);
    }


    @DeleteMapping("/delete_person/{id}")
    public void deletePersonById(@PathVariable Long id) {
        if (personAndRoleService.personExistById(id))
            personAndRoleService.deletePersonById(id);
        else
            throw new RuntimeException();
    }
}
