package com.all.Person;


import com.all.Role.Role;
import com.all.Role.RoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(Long id) {
        return personRepository.getById(id);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }


    public void deleteByNickname(String nickname) {
        personRepository.deleteByLoginFromPersonRoles(nickname);
        personRepository.deleteByLoginFromPerson(nickname);
    }
//    @Transactional
//    public void deleteByNickname(String nickname) {
//        personRepository.deleteByLoginFromPersonRoles(nickname);
//    }

    public List<String> findRolesByNickname(String nickname) {
        return personRepository.findRolesByNickname(nickname);
    }

    public Person findPersonByNickname(String nickname) {
        return personRepository.findPersonByNickname(nickname);
    }


    public void createPerson(Person person) {
        personRepository.save(person);

    }
//    public List<String> getByNickname(String nickname) {
//        return personRepository.getByNickname(nickname);
//
//    }


//    public boolean existsByNickname(String nickname) {
//        return personRepository.existsByNickname(nickname);
//    }
//
//    public Person findByNickname(String nickname) {
//        return personRepository.findByNickname(nickname);
//    }

}
