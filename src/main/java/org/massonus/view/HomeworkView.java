package org.massonus.view;

import org.massonus.entity.Homework;
import org.massonus.service.HomeworkService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HomeworkView {
    private static final HomeworkService homeworkService = new HomeworkService();

    public void workWithHomework(List<Homework> homeworks, Integer lectureId) {
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

    public static void workWithAllHomework(List<Homework> allHomework) {
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
