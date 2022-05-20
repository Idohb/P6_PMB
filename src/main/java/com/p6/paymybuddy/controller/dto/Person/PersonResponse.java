package com.p6.paymybuddy.controller.dto.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonResponse {
    private Long idPerson;
    private String firstName;
    private String lastName;
}
