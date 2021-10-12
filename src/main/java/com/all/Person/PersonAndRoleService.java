package com.all.Person;


import com.all.Role.Role;
import com.all.Role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonAndRoleService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public PersonAndRoleService(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    public Person findPersonByNickname(String nickname) {
        return personRepository.findPersonByNickname(nickname);
    }

    public Role findRoleByName(String role) {
        return roleRepository.findRoleByName(role);
    }

    public void savePerson(Person person){
        personRepository.save(person);
    }
}
