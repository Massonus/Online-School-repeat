package org.massonus.view;

import org.massonus.entity.Lecture;
import org.massonus.repo.LectureRepo;
import org.massonus.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class LectureView {
    private final LectureService lectureService;
    private final LectureRepo lectureRepo;

    @Autowired
    public LectureView(LectureService lectureService, LectureRepo lectureRepo) {
        this.lectureService = lectureService;
        this.lectureRepo = lectureRepo;
    }

    public void workWithLectures() {

        /*Map<Person, List<Lecture>> lecturesAsMap = lectureService.groupLectureByPerson(lectures);*/
        List<Lecture> lectures = lectureRepo.getLectureList();
        while (true) {

            System.out.println("\n What you want to do with Lecture?");
            System.out.println("1. Print all Lectures");
            System.out.println("2. Add new Lecture ");
            System.out.println("3. Delete Lecture");
            System.out.println("4. Get size of array");
            System.out.println("5. Sort by id");
            System.out.println("6. Print the first lecture and what have the most amount of materials");
            System.out.println("7. Print lectures grouping by teacher");
            System.out.println("0. Return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    lectures = lectureRepo.getLectureList();
                    lectureService.printAll(lectures);
                    break;

                case "2":
                    Lecture newElement = lectureService.createElementByUser();
                    lectureRepo.addLecture(newElement);
                    break;

                case "3":
                    int id = lectureService.choiceId();
                    Lecture lectureById = lectureRepo.getLectureById(id);
                    lectureRepo.deleteLecture(lectureById);
                    break;

                case "4":
                    System.out.println(lectures.size());
                    break;

                case "5":
                    lectures = lectureService.sortLectureById(lectures);
                    System.out.println(lectures);
                    break;

                case "6":
                    Lecture firstLecture = lectureService.findFirstLecture(lectures);
                    System.out.println(firstLecture);
                    break;

                case "7":
                    /*lecturesAsMap.forEach((k, v) -> System.out.println(k + " " + v));*/
                    break;

                case "0":
                    return;
            }
        }
    }
}
