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

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class CourseController {

    final MainController mainController = new MainController();
    final CourseService courseService = new CourseService();
    final CourseRepo courseRepo = new CourseRepo();
    final ControlWorkService workService = new ControlWorkService();
    final LogController logController = new LogController();

    final Logger logger = new Logger("CourseController");

    public void mainMenu(List<Course> courses) {

        while (true) {
            System.out.println("\n What you want to do?");
            System.out.println("1. Print all Courses");
            System.out.println("2. Choice the Course by id to work");
            System.out.println("3. To sort by name");
            System.out.println("4. To sort by id");
            System.out.println("5. To work with all lectures");
            System.out.println("6. To print work with all people");
            System.out.println("7. To work with all materials");
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
                    courseRepo.getAll(courses);
                    break;
                case "2":
                    Course course = Optional.ofNullable(courseRepo.getById(courses))
                            .orElse(new Course());
                    logger.info("chose course " + course.getName());
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
                        courses = courseRepo.sortCoursesById();
                    } catch (NullPointerException e) {
                        logger.warning("can't sort because array is empty ", e);
                        break;
                    }
                    break;

                case "5":
                    List<Lecture> allLectures;
                    try {
                        allLectures = courseRepo.getAllLectures();
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
                        allPeople = courseRepo.getAllPeople();
                    } catch (NullPointerException e) {
                        logger.warning("List<Person> don't created " + e);
                        System.out.println("First create an array");
                        break;
                    }
                    PersonView.workWithAllPeople(allPeople);
                    break;

                case "7":
                    List<AdditionalMaterial> allMaterials;
                    try {
                        allMaterials = courseRepo.getAllMaterials();
                    } catch (NullPointerException e) {
                        logger.warning("List<AdditionalMaterial> don't created " + e);
                        System.out.println("First create an array");
                        break;
                    }
                    AdditionalMaterialsView.workWithAllMaterials(allMaterials);
                    break;

                case "8":
                    List<Homework> allHomework;
                    try {
                        allHomework = courseRepo.getAllHomework();
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
                    Course byId = courseRepo.getById(courses);
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
        try (RandomAccessFile raf = new RandomAccessFile("src/main/java/org/massonus/log/logs.txt", "rw")) {
            raf.setLength(0);
        } catch (IOException e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
        }
        List<Course> courses = null;
        System.out.println("Do you want to create the Course?");
        System.out.println("Enter Y or any key");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.equals("Y") || s.equals("y")) {
            System.out.println("First you must create a Course");
            String choice = courseRepo.choice();
            if (choice.equals("2")) {
                courses = courseRepo.createAndFillCourseAuto();
                courseRepo.getAll(courses);
            } else {
                courses = courseRepo.createAndFillCourseByUser();
            }
        }
        return courses;
    }
}
