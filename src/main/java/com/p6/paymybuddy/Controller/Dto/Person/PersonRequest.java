package com.p6.paymybuddy.Controller.Dto.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonRequest {
    private String firstName;
    private String lastName;
}
