package org.massonus.utils;

import org.massonus.entity.Course;
import org.massonus.entity.Person;
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
import java.util.Scanner;

public class CourseController {

    final CourseService courseService = new CourseService();
    final CourseRepo courseRepo = new CourseRepo();
    final ControlWorkService workService = new ControlWorkService();
    final LogController logController = new LogController();
    private final AdditionalMaterialsView materialsView;
    private final HomeworkView homeworkView;
    private final LectureView lectureView;

    public CourseController(AdditionalMaterialsView materialsView, HomeworkView homeworkView, LectureView lectureView) {
        this.materialsView = materialsView;
        this.homeworkView = homeworkView;
        this.lectureView = lectureView;
    }

    final Logger logger = new Logger("CourseController");

    public void mainMenu(List<Course> courses) {

        while (true) {
            System.out.println("\n What you want to do?");
            System.out.println("1. Print all Courses");
            System.out.println("3. To sort by name");
            System.out.println("4. To sort by id");
            System.out.println("5. To work with lectures");
            System.out.println("6. To work with people");
            System.out.println("7. To work with materials");
            System.out.println("8. To work with homework");
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
                    lectureView.workWithLectures(courses);
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
                    homeworkView.workWithHomework(courses);
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
