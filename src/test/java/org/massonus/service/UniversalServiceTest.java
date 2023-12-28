package org.massonus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.massonus.entity.Person;
import org.massonus.entity.Role;

import java.util.ArrayList;
import java.util.List;

class UniversalServiceTest {

    private UniversalService<Person> target;
    private List<Person> check;

    @BeforeEach
    void setUp() {
        target = new PersonService();
        check = new ArrayList<>();
        check.add(new Person(1, Role.TEACHER));
    }

    @Test
    void printAll() {
        boolean result = target.printAll(check);
        Assertions.assertTrue(result);
    }
}