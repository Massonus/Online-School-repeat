package org.massonus.view;

import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.repo.AdditionalMaterialsRepo;
import org.massonus.repo.HomeworkRepo;
import org.massonus.repo.LectureRepo;
import org.massonus.repo.PersonRepo;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LectureView {
    final HomeworkView homeworkView = new HomeworkView();
    final AdditionalMaterialsView additionalMaterialsView = new AdditionalMaterialsView();
    final LectureRepo lectureRepo = new LectureRepo();

    public void workWithLecture() {

        PersonRepo.people = null;
        while (true) {
            System.out.println("\n What you want to do with Lecture?");
            System.out.println("1. Get Lecture by id to work with Homework");
            System.out.println("2. Get Lecture by id to work with Additional material");
            System.out.println("3. Print all Lectures");
            System.out.println("4. Add new Lecture by index");
            System.out.println("5. Add new Lecture to the end");
            System.out.println("6. To remove element");
            System.out.println("7. To check that array is Empty");
            System.out.println("8. To get size of array");
            System.out.println("9. To sort by index");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    Lecture lectureForHomework = lectureRepo.getById(LectureRepo.lectures);
                    System.out.println(lectureForHomework);
                    try {
                        HomeworkRepo.homeworks = lectureForHomework.getHomeworks();
                    } catch (NullPointerException e) {
                        System.out.println("Incorrect id " + e);
                        break;
                    }
                    homeworkView.workWithHomework();
                    break;

                case "2":
                    Lecture lectureForMaterial = lectureRepo.getById(LectureRepo.lectures);
                    System.out.println(lectureForMaterial);
                    try {
                        AdditionalMaterialsRepo.materials = lectureForMaterial.getMaterials();
                    } catch (NullPointerException e) {
                        System.out.println("Incorrect id " + e.getMessage());
                        break;
                    }
                    additionalMaterialsView.workWithMaterial();
                    break;

                case "3":
                    lectureRepo.getAll(LectureRepo.lectures);
                    break;

                case "4":
                    int index = lectureRepo.choiceIndex();
                    lectureRepo.add(index);
                    break;

                case "5":
                    lectureRepo.add();
                    break;

                case "6":
                    lectureRepo.removeById(LectureRepo.lectures);
                    break;

                case "7":
                    System.out.println(LectureRepo.lectures.isEmpty());
                    break;

                case "8":
                    System.out.println(LectureRepo.lectures.size());
                    break;

                case "9":
                    Collections.sort(LectureRepo.lectures);
                    break;

                case "0":
                    return;
            }
        }
    }

    public static void workWithAllLectures(List<Lecture> allLectures) {

        while (true) {
            System.out.println("\n1. To print all lectures as List");
            System.out.println("2. To print the first lecture and what have the most amount of materials");
            System.out.println("3. To print lectures grouping by teacher");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    allLectures.forEach(System.out::println);
                    break;

                case "2":
                    Lecture lecture = allLectures.stream()
                            .max(Lecture::compareTo)
                            .orElseGet(Lecture::new);

                    System.out.println(lecture);
                    System.out.println(lecture.getMaterials().size());
                    break;

                case "3":
                    Map<Person, List<Lecture>> collect = allLectures.stream()
                            .collect(Collectors.groupingBy(Lecture::getPerson));

                    collect.forEach((k, v) -> System.out.println(k + " " + v));

                    break;

                case "0":
                    return;
            }
        }
    }
}
