package org.massonus.view;

import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.Homework;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.service.LectureService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LectureView {
    private final HomeworkView homeworkView = new HomeworkView();
    private static final LectureService lectureService = new LectureService();

    public void workWithLecture(List<Lecture> lectures, List<Person> people, Integer courseId) {

        while (true) {
            System.out.println("\n What you want to do with Lecture?");
            System.out.println("1. Get Lecture by id to work with Homework");
            System.out.println("2. Get Lecture by id to work with Additional material");
            System.out.println("3. Print all Lectures");
            System.out.println("4. Add new Lecture to the end");
            System.out.println("5. To remove element");
            System.out.println("6. To check that array is Empty");
            System.out.println("7. To get size of array");
            System.out.println("8. To sort by index");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    int id1 = lectureService.choiceId();
                    Lecture lectureForHomework = lectureService.getById(lectures, id1);
                    System.out.println(lectureForHomework);
                    List<Homework> homeworks;
                    try {
                        homeworks = lectureForHomework.getHomeworks();
                    } catch (NullPointerException e) {
                        System.out.println("Incorrect id " + e);
                        break;
                    }
                    homeworkView.workWithHomework(homeworks, lectureForHomework.getId());
                    break;

                /*case "2":
                    int id2 = lectureService.choiceId();
                    Lecture lectureForMaterial = lectureService.getById(lectures, id2);
                    System.out.println(lectureForMaterial);
                    List<AdditionalMaterial> materials;
                    try {
                        materials = lectureForMaterial.getMaterials();
                    } catch (NullPointerException e) {
                        System.out.println("Incorrect id " + e.getMessage());
                        break;
                    }
                    additionalMaterialsView.workWithMaterial(materials, lectureForMaterial.getId());
                    break;*/

                case "3":
                    lectureService.printAll(lectures);
                    break;

                case "4":
                    Lecture newElement = lectureService.createElementByUser(people);
                    List<AdditionalMaterial> materialsListForLecture = lectureService.createAndFillMaterialsListForLecture(newElement.getId());
                    newElement.setMaterials(materialsListForLecture);
                    lectureService.add(newElement, lectures, courseId);
                    break;

                case "5":
                    int id = lectureService.choiceId();
                    lectureService.removeById(lectures, id);
                    break;

                case "6":
                    System.out.println(lectures.isEmpty());
                    break;

                case "7":
                    System.out.println(lectures.size());
                    break;

                case "8":
                    Collections.sort(lectures);
                    break;

                case "0":
                    return;
            }
        }
    }

    public static void workWithAllLectures(List<Lecture> allLectures) {
        Map<Person, List<Lecture>> lecturesAsMap = lectureService.groupLectureByPerson(allLectures);

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
                    Lecture firstLecture = lectureService.findFirstLecture(allLectures);
                    System.out.println(firstLecture);
                    System.out.println(firstLecture.getMaterials().size());
                    break;

                case "3":
                    lecturesAsMap.forEach((k, v) -> System.out.println(k + " " + v));
                    break;

                case "0":
                    return;
            }
        }
    }
}
