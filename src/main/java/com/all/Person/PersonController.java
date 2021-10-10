package com.all.Person;

import com.all.Role.Role;
import com.all.Role.RoleRepository;
import com.all.Role.RoleResponse;
import com.all.Role.RoleService;
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

    @GetMapping()
    public String hello() {
        return "hello";
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
    public void createPerson(Person person) {
        personService.savePerson(person);
    }

    @GetMapping("/delete_user/{nickname}")
    public void deletePerson(@PathVariable String nickname) {
        personService.deleteByNickname(nickname);

    }

//    @GetMapping("user_delete")
//    public void deletePerson(@PathVariable("nickname") String nickname) {
//        personService.deleteByNickname(nickname);
//    }
//    @GetMapping("user_delete/{nickname}")
//    public void deleteTweet(@PathVariable("nickname") String nickname) {
//        personService.deleteByNickname(nickname);
//    }

//    @GetMapping("user/{nickname}")
//    public List<String> getByNickname(String nickname) {
//        return personService.getByNickname(nickname);  //.stream().map((String t) -> Role.getRole(t)).collect(Collectors.toList());
//    }

//    GetMapping("/spesific_person/{id}")
//    public String getById(@PathVariable("id") String login){
//
//        personService.findById(login);
//        return "redirect:/persons";
//    }


}
