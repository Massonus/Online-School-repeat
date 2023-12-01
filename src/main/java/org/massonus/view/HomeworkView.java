package org.massonus.view;

import org.massonus.entity.Homework;
import org.massonus.repo.HomeworkRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class HomeworkView {
    final HomeworkRepo homeworkRepo = new HomeworkRepo();

    public void workWithHomework() {
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
                    homeworkRepo.getAll(HomeworkRepo.homeworks);
                    break;

                case "2":
                    int index = homeworkRepo.choiceIndex();
                    homeworkRepo.add(index);
                    break;

                case "3":
                    homeworkRepo.add();
                    break;

                case "4":
                    homeworkRepo.removeById(HomeworkRepo.homeworks);
                    break;

                case "5":
                    System.out.println(HomeworkRepo.homeworks.isEmpty());
                    break;

                case "6":
                    System.out.println(HomeworkRepo.homeworks.size());
                    break;

                case "0":
                    return;
            }
        }
    }

    public static void workWithAllHomework(List<Homework> allHomework) {

        while (true) {
            System.out.println("\n1. To print all homework as List");
            System.out.println("2. To sort all homework");
            System.out.println("3. To print all homework as Map");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    if (allHomework != null) {
                        for (Homework homework : allHomework) {
                            System.out.println(homework);
                        }
                    }
                    break;

                case "2":
                    if (allHomework != null) {
                        allHomework = allHomework.stream()
                                .sorted(Comparator.comparing(Homework::getLectureId))
                                .toList();
                        allHomework.forEach(System.out::println);
                    }

                    break;

                case "3":
                    HomeworkRepo.homeworkMap.forEach((k, v) -> System.out.println("lectureID: " + k + " " + v));
                    break;

                case "0":
                    return;
            }
        }
    }
}
