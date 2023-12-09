package org.massonus.view;

import org.massonus.entity.Homework;
import org.massonus.repo.HomeworkRepo;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HomeworkView {
    private static final HomeworkRepo homeworkRepo = new HomeworkRepo();

    public void workWithHomework(List<Homework> homeworks) {
        while (true) {
            System.out.println("\n What you want to do with Homework?");
            System.out.println("1. Print all Homeworks");
            System.out.println("2. Add new Homework by index");
            System.out.println("3. Add new Homework to the end");
            System.out.println("4. To remove element");
            System.out.println("5. To check that array is Empty");
            System.out.println("6. To get size of array");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    homeworkRepo.getAll(homeworks);
                    break;

                case "2":
                    int index = homeworkRepo.choiceIndex();
                    homeworkRepo.add(index);
                    break;

                case "3":
                    homeworkRepo.add();
                    break;

                case "4":
                    homeworkRepo.removeById(homeworks);
                    break;

                case "5":
                    System.out.println(homeworks.isEmpty());
                    break;

                case "6":
                    System.out.println(homeworks.size());
                    break;

                case "0":
                    return;
            }
        }
    }

    public static void workWithAllHomework(List<Homework> allHomework) {
        Map<Integer, List<Homework>> homeworksAsMap = homeworkRepo.groupHomeworksByLectureId(allHomework);

        while (true) {
            System.out.println("\n1. To print all homework as List");
            System.out.println("2. To sort all homework");
            System.out.println("3. To print all homework as Map");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    allHomework.forEach(System.out::println);
                    break;

                case "2":
                    allHomework = homeworkRepo.sortHomeworkByLectureId(allHomework);
                    break;

                case "3":
                    homeworksAsMap.forEach((k, v) -> System.out.println("lectureId " + k + " " + v));
                    break;

                case "0":
                    return;
            }
        }
    }
}
