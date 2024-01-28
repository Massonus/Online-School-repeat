package org.massonus.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.massonus.entity.Homework;
import org.massonus.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class HomeworkView {
    private static final Logger logger = LogManager.getLogger(HomeworkView.class);
    private final HomeworkService homeworkService;

    @Autowired
    public HomeworkView(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    public void workWithHomework() {
        List<Homework> homeworkList = homeworkService.getHomeworkList();
        while (true) {


            System.out.println("\n Make your choice (use only numbers)");
            System.out.println("1. Print all Homeworks");
            System.out.println("2. Add new Homework");
            System.out.println("3. Change information about homework by id");
            System.out.println("4. Delete element");
            System.out.println("5. Get size of array");
            System.out.println("6. Sort all homework by lectureId");
            System.out.println("7. Print all homework as Map");
            System.out.println("0. Return");

            Scanner scanner = new Scanner(System.in);
            String select = scanner.nextLine();

            switch (select) {

                case "1":
                    homeworkList = homeworkService.getHomeworkList();
                    homeworkService.printAll(homeworkList);
                    break;

                case "2":
                    final Homework elementByUser = homeworkService.createElementByUser();
                    homeworkService.saveHomework(elementByUser);
                    break;

                case "3":
                    int id = homeworkService.choiceId();
                    Homework refactoredHomework = homeworkService.homeworkRefactor(homeworkService.getHomeworkById(id).get());
                    homeworkService.saveHomework(refactoredHomework);
                    break;

                case "4":
                    homeworkService.deleteHomework(homeworkService.choiceId());
                    break;

                case "5":
                    System.out.println(homeworkList.size());
                    break;

                /*case "6":
                    allHomework = homeworkService.sortHomeworkByLectureId(allHomework);
                    break;

                case "7":
                    homeworksAsMap.forEach((k, v) -> System.out.println("lectureID " + k + " " + v));
                    break;*/

                case "0":
                    return;
            }
        }
    }
}
