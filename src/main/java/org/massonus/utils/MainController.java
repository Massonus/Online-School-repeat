package org.massonus.utils;

import org.massonus.entity.Course;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.log.Logger;
import org.massonus.repo.LectureRepo;
import org.massonus.repo.PersonRepo;
import org.massonus.view.LectureView;
import org.massonus.view.PersonView;

import java.util.List;
import java.util.Scanner;

public class MainController {
    final LectureView lectureView = new LectureView();
    final PersonView personView = new PersonView();
    final Logger logger = new Logger("MainController");

    public void workWithCourseElements(Course course) {
        List<Lecture> lectures;
        List<Person> people = course.getPeople();

        while (true) {
            System.out.println("\n Make your choice (use only numbers)");
            System.out.println("1. Select the Lecture");
            System.out.println("2. Select the Person");
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

                    try {
                        lectures = course.getLectures();
                    } catch (NullPointerException e) {
                        System.out.println("Incorrect id " + e);
                        break;
                    }
                    lectureView.workWithLecture(lectures, people);
                    break;

                case 2:
                    personView.workWithPerson(people);
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
}
