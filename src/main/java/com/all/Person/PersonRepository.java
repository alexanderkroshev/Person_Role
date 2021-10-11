package com.all.Person;

import com.all.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value ="SELECT * FROM Person where nickname= :nickname", nativeQuery = true)
    @Transactional(readOnly = true)
    Person findPersonByNickname(@Param("nickname") String nickname);
   }