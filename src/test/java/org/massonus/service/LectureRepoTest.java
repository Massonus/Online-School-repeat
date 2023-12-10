package org.massonus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.massonus.entity.Person;
import org.massonus.repo.LectureRepo;
import org.massonus.repo.PersonRepo;

import java.util.List;

class LectureRepoTest {

    private PersonRepo personRepo;
    private LectureRepo target;

    @BeforeEach
    void setUp() {
        target = new LectureRepo();
        personRepo = new PersonRepo();
    }

    @Test
    void shouldCreateLectureContainsPerson() {
        List<Person> people = personRepo.createAndFillListAuto(2);
        Person result = target.getPersonForLectureAuto(people);
        boolean contains = people.contains(result);
        Assertions.assertTrue(contains);
    }
}

