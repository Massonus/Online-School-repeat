package org.massonus.view_toDelete;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.massonus.entity.Course;
import org.massonus.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Component
public class CourseView {
    private static final Logger logger = LogManager.getLogger(CourseView.class);
    private final CourseService courseService;

    @Autowired
    public CourseView(CourseService courseService) {
        this.courseService = courseService;

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            context.setConfigLocation(CourseView.class.getResource("/log4j.xml").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void workWithCourses() {
        List<Course> courses = courseService.getCourseList();
        while (true) {

            System.out.println("\n What do you want to do?");
            System.out.println("1. Print all Courses");
            System.out.println("2. Sort by id");
            System.out.println("3. Sort by name");
            System.out.println("4. Make serialization");
            System.out.println("5. Print deserialization");
            System.out.println("0. Return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    courses = courseService.getCourseList();
                    courseService.printAll(courses);
                    break;

                case "2":
                    try {
                        courses = courseService.sortCoursesById(courses);
                        System.out.println(courses);
                    } catch (NullPointerException e) {
                        logger.warn("can't sort because array is empty ", e);
                        break;
                    }
                    break;

                case "3":
                    try {
                        Collections.sort(courses);
                        System.out.println(courses);
                    } catch (NullPointerException e) {
                        logger.warn("can't sort because array is empty ", e);
                        break;
                    }
                    break;

                case "4":
                    int id1 = courseService.choiceId();
                    Course courseById = courseService.getCourseById(id1).orElse(new Course());
                    courseService.serial(courseById);
                    break;

                case "5":
                    Course deSer = courseService.deSer();
                    System.out.println(deSer);
                    break;

                case "0":
                    logger.info("return");
                    return;

                default:
                    logger.warn("incorrect symbol: " + choice);
                    System.out.println("Incorrect");
            }
        }
    }
}
