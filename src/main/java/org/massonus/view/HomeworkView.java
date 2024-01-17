package org.massonus.view;

import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.Course;
import org.massonus.entity.Homework;
import org.massonus.entity.Lecture;
import org.massonus.log.Logger;
import org.massonus.service.CourseService;
import org.massonus.service.HomeworkService;
import org.massonus.service.LectureService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HomeworkView {
    private static final Logger logger = new Logger("HomeworkView");
    private final HomeworkService homeworkService = new HomeworkService();
    private final LectureService lectureService = new LectureService();
    private final CourseService courseService = new CourseService();

    public void workWithHomework(List<Course> courses) {

        while (true) {
            System.out.println("\n Make your choice (use only numbers)");
            System.out.println("1. To work with special homework list");
            System.out.println("2. To work with all homework list");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            int select;

            try {
                select = scanner.nextInt();
            } catch (Exception e) {
                for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                    System.out.println(stackTraceElement);
                }
                select = 69;
            }

            switch (select) {
                case 1:
                    List<Lecture> allLectures = courseService.getAllLectures(courses);
                    allLectures.forEach(System.out::println);
                    int id = lectureService.choiceId();
                    Lecture specialLectureForHomework = lectureService.getById(allLectures, id);
                    List<Homework> homeworks = specialLectureForHomework.getHomeworks();
                    workWithSpecialHomework(homeworks, specialLectureForHomework.getId());
                    break;

                case 2:
                    List<Homework> allHomework = courseService.getAllHomework(courses);
                    workWithAllHomework(allHomework);
                    break;

                case 69:
                    logger.warning("incorrect symbol: " + select);
                    System.out.println("Incorrect number");
                    break;
                case 0:
                    logger.info("returned to CourseController");
                    return;
            }
        }
    }

    private void workWithSpecialHomework(List<Homework> homeworks, Integer lectureId) {
        while (true) {
            System.out.println("\n What you want to do with Homework?");
            System.out.println("1. Print all Homeworks");
            System.out.println("2. Add new Homework");
            System.out.println("3. To remove element");
            System.out.println("4. To check that array is Empty");
            System.out.println("5. To get size of array");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    homeworkService.printAll(homeworks);
                    break;

                case "2":
                    homeworkService.add(homeworks, lectureId);
                    break;

                case "3":
                    int id = homeworkService.choiceId();
                    homeworkService.removeById(homeworks, id);
                    break;

                case "4":
                    System.out.println(homeworks.isEmpty());
                    break;

                case "5":
                    System.out.println(homeworks.size());
                    break;

                case "0":
                    return;
            }
        }
    }

    private void workWithAllHomework(List<Homework> allHomework) {
        Map<Integer, List<Homework>> homeworksAsMap = homeworkService.groupHomeworksByLectureId(allHomework);

        while (true) {
            System.out.println("\n1. To print all homework as List");
            System.out.println("2. To sort all homework by lectureId");
            System.out.println("3. To print all homework as Map");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    allHomework.forEach(System.out::println);
                    break;

                case "2":
                    allHomework = homeworkService.sortHomeworkByLectureId(allHomework);
                    break;

                case "3":
                    homeworksAsMap.forEach((k, v) -> System.out.println("lectureID " + k + " " + v));
                    break;

                case "0":
                    return;
            }
        }
    }
}
