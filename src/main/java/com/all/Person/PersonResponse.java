package com.all.Person;


import com.all.Role.RoleResponse;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PersonResponse implements Serializable {

    private final String login;
    private final String password;
    private final List<RoleResponse> roleList;

    public static PersonResponse fromPerson(Person person) {
        List<RoleResponse> roleResponses = person.getRoles().stream()
                .map(RoleResponse::fromRole).collect(Collectors.toList());
        return new PersonResponse(person.getNickname(), person.getPassword(), roleResponses);
    }
}

