package org.massonus.view;

import org.massonus.entity.Lecture;
import org.massonus.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class LectureView {
    private final LectureService lectureService;

    @Autowired
    public LectureView(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    public void workWithLectures() {

        /*Map<Person, List<Lecture>> lecturesAsMap = lectureService.groupLectureByPerson(lectures);*/
        List<Lecture> lectures = lectureService.getLectureList();
        while (true) {

            System.out.println("\n What you want to do with Lecture?");
            System.out.println("1. Print all Lectures");
            System.out.println("2. Add new Lecture ");
            System.out.println("3. Change information about lecture by id");
            System.out.println("4. Delete Lecture");
            System.out.println("5. Get size of array");
            System.out.println("6. Sort by id");
            System.out.println("7. Print the first lecture and what have the most amount of materials");
            System.out.println("8. Print lectures grouping by teacher");
            System.out.println("0. Return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    lectures = lectureService.getLectureList();
                    lectureService.printAll(lectures);
                    break;

                case "2":
                    Lecture newElement = lectureService.createElementByUser();
                    lectureService.saveLecture(newElement);
                    break;

                case "3":
                    int id = lectureService.choiceId();
                    Lecture lecture = lectureService.lectureRefactor(lectureService.getLectureById(id).orElse(new Lecture()));
                    lectureService.saveLecture(lecture);
                    break;

                case "4":
                    lectureService.deleteLecture(lectureService.choiceId());
                    break;

                case "5":
                    System.out.println(lectures.size());
                    break;

                case "6":
                    lectures = lectureService.sortLectureById(lectures);
                    lectureService.printAll(lectures);
                    break;

                case "7":
                    Lecture firstLecture = lectureService.findFirstLecture(lectures);
                    System.out.println(firstLecture);
                    break;

                case "8":
                    /*lecturesAsMap.forEach((k, v) -> System.out.println(k + " " + v));*/
                    break;

                case "0":
                    return;
            }
        }
    }
}
