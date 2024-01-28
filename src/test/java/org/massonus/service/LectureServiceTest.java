package org.massonus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.entity.Role;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

        expectedPerson = new Person();
        expectedPerson.setId(2L);
        expectedPerson.setRole(Role.TEACHER);
        expectedLecture = new Lecture();
        expectedLecture.setId(1L);
        expectedLecture.setSubject("Math");
        expectedLecture.setPerson(expectedPerson);
        expectedLecture.setMaterials(List.of(new AdditionalMaterial(), new AdditionalMaterial()));

        Lecture lecture = new Lecture();
        lecture.setId(2L);
        lecture.setSubject("Math");
        Person person = new Person();
        person.setId(1L);
        person.setRole(Role.TEACHER);
        lecture.setPerson(person);
        lecture.setMaterials(List.of(new AdditionalMaterial()));

        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_oldExample.xml");
        target = context.getBean("lectureService", LectureService.class);

        people = List.of(person, expectedPerson);
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