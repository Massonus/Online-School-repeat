package org.massonus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

class LectureServiceTest {
    private LectureService target;
    private List<Lecture> check;
    private List<Person> people;
    private Lecture expectedLecture;
    private Person expectedPerson;

    @BeforeEach
    void setUp() {
        expectedPerson = new Person(2, Role.STUDENT);
        expectedLecture = new Lecture(1, "Math", expectedPerson, List.of(new AdditionalMaterial("Math"), new AdditionalMaterial("Geo")));
        Lecture lecture = new Lecture(2, "Math", new Person(1, Role.TEACHER), List.of(new AdditionalMaterial("Math")));
        target = new LectureService();
        people = List.of(new Person(1, Role.TEACHER), new Person(2, Role.TEACHER));
        check = new ArrayList<>();
        check.add(lecture);
        check.add(expectedLecture);
    }

    /*@Test
    void shouldDelete() {
        target.removeById(check, 1);
        boolean result = check.contains(expectedLecture);
        Assertions.assertFalse(result);
    }*/

    @Test
    void getById() {
        Lecture result = target.getById(check, 1);
        Assertions.assertEquals(result, expectedLecture);
    }

    /*@Test
    void shouldAdd() {
        boolean add = target.add(expectedLecture, check, 2);
        target.add(expectedLecture);
        Assertions.assertTrue(add);
    }*/

    @Test
    void shouldFindTheFirstLecture() {
        Lecture result = target.findFirstLecture(check);
        Assertions.assertEquals(result.getMaterials().size(), expectedLecture.getMaterials().size());
    }

    @Test
    void shouldThrowException() {
        check.clear();
        Assertions.assertThrows(NoSuchElementException.class, () -> target.findFirstLecture(check));
    }

    @Test
    void shouldMakeMap() {
        Map<Person, List<Lecture>> personListMap = target.groupLectureByPerson(check);
        List<Lecture> lectures = personListMap.get(expectedPerson);
        Lecture lecture = lectures.get(0);
        Assertions.assertEquals(lecture, expectedLecture);
    }
}