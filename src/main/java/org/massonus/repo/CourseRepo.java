package org.massonus.repo;

import org.massonus.entity.*;
import org.massonus.log.Logger;
import org.massonus.service.CourseService;

import java.util.*;

public class CourseRepo implements AboutRepo<Course> {
    Set<Course> courseSet;
    public List<Course> courses;
    final CourseService courseService = new CourseService();
    final LectureRepo lectureRepo = new LectureRepo();
    final PersonRepo personRepo = new PersonRepo();
    final Logger logger = new Logger("CourseRepo");

    public List<Course> createAndFillCourseAuto() {
        Random random = new Random();
        int lengthMas = random.nextInt(1, 5);
        courseSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            CourseService.createCourseIdAuto();
            Course course = courseService.createElementAuto();

            List<Person> people = personRepo.createAndFillListAuto();
            course.setPeople(people);

            List<Lecture> lectures = lectureRepo.createAndFillListAuto(people);
            course.setLectures(lectures);


            logger.info("course created automatically with index: " + i + " id: " + course.getId());
            logger.info("course was added - " + courseSet.add(course));
            logger.info("lectures: " + lectures.size());
            logger.info("people: " + people.size());
        }
        logger.info("Created " + courseSet.size() + " courses");
        courses = new ArrayList<>(courseSet);
        logger.info("List created successful, size : " + lengthMas);
        return courses;
    }

    public List<Course> createAndFillCourseByUser() {
        int length = lengthMas();
        courses = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            CourseService.createCourseIdByUser();
            CourseService.createCourseNameByUser();
            Course course = courseService.createElementByUser();

            List<Person> people = personRepo.createAndFillListByUser();
            course.setPeople(people);

            List<Lecture> lectures = lectureRepo.createAndFillListByUser(people);
            course.setLectures(lectures);

            courses.add(course);
            logger.info("course created by user with index: " + i + " id: " + course.getId());

        }
        return courses;
    }

    public List<AdditionalMaterial> getAllMaterials() {
        List<AdditionalMaterial> materials = getAllLectures().stream()
                .map(Lecture::getMaterials)
                .flatMap(Collection::stream)
                .toList();
        logger.info("List of materials created " + materials.size());
        return materials;
    }

    public List<Homework> getAllHomework() {
        List<Homework> homeworkList = getAllLectures().stream()
                .map(Lecture::getHomeworks)
                .flatMap(Collection::stream)
                .toList();
        logger.info("List of homework created " + homeworkList.size());
        return homeworkList;
    }

    public List<Lecture> getAllLectures() {
        List<Lecture> lectures = courses.stream()
                .map(Course::getLectures)
                .flatMap(Collection::stream)
                .toList();
        logger.info("List of lectures created " + lectures.size());
        return lectures;
    }

    public List<Person> getAllPeople() {
        List<Person> people = courses.stream()
                .map(Course::getPeople)
                .flatMap(Collection::stream)
                .toList();
        logger.info("List of people created " + people.size());
        return people;
    }

    public List<Course> sortCoursesById() {
        return courses = courses.stream()
                .sorted(Comparator.comparing(Course::getId))
                .toList();
    }
}
