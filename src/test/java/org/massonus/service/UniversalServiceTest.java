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
    private Person expected;

    @BeforeEach
    void setUp() {
        expected = new Person(2, Role.STUDENT);
        target = new PersonService();
        check = new ArrayList<>();
        check.add(new Person(1, Role.TEACHER));
        check.add(expected);
    }

    @Test
    void shouldDelete() {
        target.removeById(check, 2);
        boolean result = check.contains(expected);
        Assertions.assertFalse(result);
    }

    @Test
    void getById() {
        Person result = target.getById(check, 2);
        Assertions.assertEquals(result, expected);
    }

    @Test
    void printAll() {
        boolean result = target.printAll(check);
        Assertions.assertTrue(result);
    }
}