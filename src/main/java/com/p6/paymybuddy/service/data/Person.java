package com.p6.paymybuddy.service.data;

import lombok.Data;

@Data
public class Person {
    private Long id;
    private String firstName;
    private String lastName;

    public Person() {
    }

}
