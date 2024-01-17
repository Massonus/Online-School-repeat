package org.massonus.utils;

import org.massonus.entity.*;
import org.massonus.log.Logger;
import org.massonus.repo.CourseRepo;
import org.massonus.service.ControlWorkService;
import org.massonus.service.CourseService;
import org.massonus.view.AdditionalMaterialsView;
import org.massonus.view.HomeworkView;
import org.massonus.view.LectureView;
import org.massonus.view.PersonView;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CourseController {

    final MainController mainController = new MainController();
    final CourseService courseService = new CourseService();
    final CourseRepo courseRepo = new CourseRepo();
    final ControlWorkService workService = new ControlWorkService();
    final LogController logController = new LogController();
    private AdditionalMaterialsView materialsView;

    public CourseController(AdditionalMaterialsView materialsView) {
        this.materialsView = materialsView;
    }

    final Logger logger = new Logger("CourseController");

    public void mainMenu(List<Course> courses) {

        while (true) {
            System.out.println("\n What you want to do?");
            System.out.println("1. Print all Courses");
            System.out.println("2. Choice the Course by id to work");
            System.out.println("3. To sort by name");
            System.out.println("4. To sort by id");
            System.out.println("5. To work with all lectures");
            System.out.println("6. To work with all people");
            System.out.println("7. To work with materials");
            System.out.println("8. To work with all homework");
            System.out.println("9. To work with logs");
            System.out.println("10. To start control work");
            System.out.println("11. To make serialization");
            System.out.println("12. To print deserialization");
            System.out.println("0. Exit");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    courseService.printAll(courses);
                    break;
                case "2":
                    int id = courseService.choiceId();
                    Course course = Optional.ofNullable(courseService.getById(courses, id))
                            .orElse(new Course());
                    logger.info("chose course " + course.getCourseName());
                    mainController.workWithCourseElements(course);
                    break;

                case "3":
                    try {
                        Collections.sort(courses);
                    } catch (NullPointerException e) {
                        logger.warning("can't sort because array is empty ", e);
                        break;
                    }
                    break;

                case "4":
                    try {
                        courses = courseService.sortCoursesById(courses);
                    } catch (NullPointerException e) {
                        logger.warning("can't sort because array is empty ", e);
                        break;
                    }
                    break;

                case "5":
                    List<Lecture> allLectures;
                    try {
                        allLectures = courseService.getAllLectures(courses);
                    } catch (NullPointerException e) {
                        logger.warning("List<Lecture> don't created " + e);
                        System.out.println("First create an array");
                        break;
                    }
                    LectureView.workWithAllLectures(allLectures);
                    break;

                case "6":
                    List<Person> allPeople;
                    try {
                        allPeople = courseService.getAllPeople(courses);
                    } catch (NullPointerException e) {
                        logger.warning("List<Person> don't created " + e);
                        System.out.println("First create an array");
                        break;
                    }
                    PersonView.workWithAllPeople(allPeople);
                    break;

                case "7":
                    materialsView.workWithMaterials(courses);
                    break;

                case "8":
                    List<Homework> allHomework;
                    try {
                        allHomework = courseService.getAllHomework(courses);
                    } catch (NullPointerException e) {
                        logger.warning("List<Homework> don't created " + e);
                        System.out.println("First create an array");
                        break;
                    }
                    HomeworkView.workWithAllHomework(allHomework);
                    break;

                case "9":
                    logController.loggerMenu();
                    break;

                case "10":
                    workService.startControlWork();
                    break;

                case "11":
                    int id1 = courseService.choiceId();
                    Course byId = courseService.getById(courses, id1);
                    courseService.serial(byId);
                    break;

                case "12":
                    Course deSer = courseService.deSer();
                    System.out.println(deSer);
                    break;

                case "0":
                    logger.info("exit program");
                    System.exit(0);

                default:
                    logger.warning("incorrect symbol: " + choice);
                    System.out.println("Incorrect");
            }
        }
    }

    public List<Course> firstCreate() {

        return courseRepo.getAllCourses();
    }
}
