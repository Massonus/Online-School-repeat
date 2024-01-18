package org.massonus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.massonus.entity.Person;
import org.massonus.entity.Role;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

class UniversalServiceTest {

    private UniversalService<Person> target;
    private List<Person> check;

    @BeforeEach
    void setUp() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_oldExample.xml");
        target = context.getBean("personService", PersonService.class);
        check = new ArrayList<>();
        Person person = new Person();
        person.setId(1);
        person.setRole(Role.TEACHER);
        check.add(person);
    }

    @Test
    void printAll() {
        boolean result = target.printAll(check);
        Assertions.assertTrue(result);
    }
}