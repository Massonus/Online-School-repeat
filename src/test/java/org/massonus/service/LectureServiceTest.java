package org.massonus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.entity.Role;
import org.massonus.repo.LectureRepo;

import java.util.List;

class LectureServiceTest {

    private LectureService target;
    private List<Lecture> check;

    @BeforeEach
    void setUp() {
        target = new LectureService();
        LectureRepo lectureRepo = new LectureRepo();
        check = lectureRepo.createAndFillListAuto(List.of(new Person(1, Role.TEACHER), new Person(2, Role.STUDENT)));
    }

    @Test
    void shouldAdd() {
        boolean add = target.add(check, "2");
        Assertions.assertTrue(add);
    }

    @Test
    void shouldDelete() {
        Person person = new Person(2, Role.STUDENT);
        target.removeById(check, 2);
        boolean contains = check.contains(person);
        Assertions.assertFalse(contains);

    }
}