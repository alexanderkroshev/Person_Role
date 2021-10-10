package com.all.Person;

import com.all.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {

    //    @Query(value = "select exists(select 1 from person where nickname=:nickname)", nativeQuery = true)
//    Boolean existsByNickname(@Param("nickname") String nickname);
//
//    @Query(value = "select * from person where nickname=:nickname", nativeQuery = true)
//    Person findByNickname(@Param("nickname") String nickname);

    @Modifying
    @Transactional
    @Query(value = "delete from person_roles where persons_nickname= :nickname", nativeQuery = true)
    void deleteByLoginFromPersonRoles(@Param("nickname") String nickname);

//    @Query(value = "select roles_id from person_roles where persons_nickname=:nickname", nativeQuery = true)
//    @Transactional(readOnly = true)
//    List<Long> findByNickname(@Param("nickname") String nickname);


    @Query(value ="SELECT role FROM ROLE where ROLE.id IN(Select roles_id FROM  person_roles where persons_nickname= :nickname)", nativeQuery = true)
    @Transactional(readOnly = true)
    List<String> findRolesByNickname(@Param("nickname") String nickname);

//    @Query(value ="SELECT * FROM Person where nickname= :nickname", nativeQuery = true)
//    @Transactional(readOnly = true)
//    List<String> findPersonByNickname(@Param("nickname") String nickname);

    @Query(value ="SELECT * FROM Person where nickname= :nickname", nativeQuery = true)
    @Transactional(readOnly = true)
    Person findPersonByNickname(@Param("nickname") String nickname);

    @Modifying
    @Transactional
    @Query(value = "delete from person where person.nickname=:nickname", nativeQuery = true)
    void deleteByLoginFromPerson(@Param("nickname") String nickname);

//    @Query(value = "select roles_id from person_roles where persons_nickname=:nickname", nativeQuery = true)
//    @Transactional(readOnly = true)
//    List<Role> findByNickname(@Param("nickname") String nickname);

//    @Modifying
//    @Query(value = "delete from person where person.nickname=:nickname", nativeQuery = true)
//    void deleteByLoginFromPerson(@Param("nickname") String nickname);


//    @Query(value = "select * from person_roles where persons_nickname=:nickname", nativeQuery = true)
//    List<String> getByNickname(@Param("nickname") String nickname);


//    @Query(value = "select from person where nickname=:nickname", nativeQuery = true)
//    Person getByNickname(@Param("nickname") String nickname);

}