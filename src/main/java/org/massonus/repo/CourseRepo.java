package org.massonus.repo;

import org.massonus.entity.Course;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.log.Logger;
import org.massonus.service.CourseService;

import java.util.*;

public class CourseRepo implements UniversalRepository {
    Set<Course> courseSet;
    private List<Course> courses;
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

            List<Person> people = personRepo.createAndFillListAuto(lengthMasAuto());
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
        int length = lengthMasByUser();
        courses = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            CourseService.createCourseIdByUser();
            CourseService.createCourseNameByUser();
            Course course = courseService.createElementByUser();

            List<Person> people = personRepo.createAndFillListByUser(lengthMasByUser());
            course.setPeople(people);

            List<Lecture> lectures = lectureRepo.createAndFillListByUser(people);
            course.setLectures(lectures);

            courses.add(course);
            logger.info("course created by user with index: " + i + " id: " + course.getId());

        }
        return courses;
    }
}
