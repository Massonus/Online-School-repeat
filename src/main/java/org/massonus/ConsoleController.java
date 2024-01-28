package org.massonus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.massonus.entity.Course;
import org.massonus.service.ControlWorkService;
import org.massonus.service.CourseService;
import org.massonus.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URISyntaxException;
import java.util.Scanner;

@Controller
public class ConsoleController {

    private static final Logger logger = LogManager.getLogger(ConsoleController.class);
    private final CourseView courseView;
    private final AdditionalMaterialView materialsView;
    private final HomeworkView homeworkView;
    private final LectureView lectureView;
    private final PersonView personView;
    private final LogView logView;
    private final CourseService courseService;
    private final ControlWorkService workService = new ControlWorkService();

    @Autowired
    public ConsoleController(CourseView courseView, AdditionalMaterialView materialsView, HomeworkView homeworkView, LectureView lectureView, PersonView personView, LogView logView, CourseService courseService) {
        this.courseView = courseView;
        this.materialsView = materialsView;
        this.homeworkView = homeworkView;
        this.lectureView = lectureView;
        this.personView = personView;
        this.logView = logView;
        this.courseService = courseService;

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            context.setConfigLocation(ConsoleController.class.getResource("/log4j.xml").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void mainMenu() {
        while (true) {

            System.out.println("\n What do you want to do?");
            System.out.println("1. Work with courses");
            System.out.println("2. Work with lectures");
            System.out.println("3. Work with people");
            System.out.println("4. Work with materials");
            System.out.println("5. Work with homework");
            System.out.println("6. Work with logs");
            System.out.println("7. Start control work");
            System.out.println("0. Exit");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    courseView.workWithCourses();
                    break;

                case "2":
                    lectureView.workWithLectures();
                    break;

                case "3":
                    personView.workWithPeople();
                    break;

                case "4":
                    materialsView.workWithMaterials();
                    break;

                case "5":
                    homeworkView.workWithHomework();
                    break;

                case "6":
                    logView.loggerMenu();
                    break;

                case "7":
                    workService.startControlWork();
                    break;

                case "0":
                    logger.info("exit program");
                    System.exit(0);
                    break;

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
