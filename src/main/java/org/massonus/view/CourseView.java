package org.massonus.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.massonus.entity.Course;
import org.massonus.repo.CourseRepo;
import org.massonus.service.AdditionalMaterialService;
import org.massonus.service.ControlWorkService;
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
    private final CourseRepo courseRepo;
    private final AdditionalMaterialView materialsView;
    private final HomeworkView homeworkView;
    private final LectureView lectureView;
    private final PersonView personView;
    private final ControlWorkService workService = new ControlWorkService();
    private final LogView logView;

    @Autowired
    public CourseView(CourseService courseService, CourseRepo courseRepo, AdditionalMaterialView materialsView, HomeworkView homeworkView, LectureView lectureView, PersonView personView, LogView logView) {
        this.courseService = courseService;
        this.courseRepo = courseRepo;
        this.materialsView = materialsView;
        this.homeworkView = homeworkView;
        this.lectureView = lectureView;
        this.personView = personView;
        this.logView = logView;

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            context.setConfigLocation(AdditionalMaterialService.class.getResource("/log4j.xml").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void mainMenu() {
        List<Course> courses = courseRepo.getCourseList();
        while (true) {
            System.out.println("\n What you want to do?");
            System.out.println("1. Print all Courses");
            System.out.println("2. To sort by id");
            System.out.println("3. To sort by name");
            System.out.println("4. To work with lectures");
            System.out.println("5. To work with people");
            System.out.println("6. To work with materials");
            System.out.println("7. To work with homework");
            System.out.println("8. To work with logs");
            System.out.println("9. To start control work");
            System.out.println("10. To make serialization");
            System.out.println("11. To print deserialization");
            System.out.println("0. Exit");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    courseService.printAll(courses);
                    break;

                case "2":
                    try {
                        courses = courseService.sortCoursesById(courses);
                    } catch (NullPointerException e) {
                        logger.warn("can't sort because array is empty ", e);
                        break;
                    }
                    break;

                case "3":
                    try {
                        Collections.sort(courses);
                    } catch (NullPointerException e) {
                        logger.warn("can't sort because array is empty ", e);
                        break;
                    }
                    break;

                case "4":
                    lectureView.workWithLectures();
                    break;

                case "5":
                    personView.workWithPeople();
                    break;

                case "6":
                    materialsView.workWithMaterials();
                    break;

                case "7":
                    homeworkView.workWithHomework();
                    break;

                case "8":
                    logView.loggerMenu();
                    break;

                case "9":
                    workService.startControlWork();
                    break;

                case "10":
                    int id1 = courseService.choiceId();
                    Course courseById = courseRepo.getCourseById(id1);
                    courseService.serial(courseById);
                    break;

                case "11":
                    Course deSer = courseService.deSer();
                    System.out.println(deSer);
                    break;

                case "0":
                    logger.info("exit program");
                    System.exit(0);

                default:
                    logger.warn("incorrect symbol: " + choice);
                    System.out.println("Incorrect");
            }
        }
    }

    public void firstInitialize() {
        for (int i = 0; i < 4; i++) {
            Course elementAuto = courseService.createElementAuto();
            System.out.println(elementAuto);
        }

    }
}
