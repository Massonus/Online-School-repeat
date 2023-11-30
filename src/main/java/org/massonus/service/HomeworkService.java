package org.massonus.service;

import org.massonus.entity.Homework;

import java.util.Random;
import java.util.Scanner;

public class HomeworkService {
    Homework homework;

    public Homework createElementByUser() {
        homework = new Homework();
        System.out.println("Enter id of homework");
        Scanner scanner1 = new Scanner(System.in);
        int id = scanner1.nextInt();
        homework.setId(id);

        System.out.println("Enter task of homework");
        Scanner scanner2 = new Scanner(System.in);
        String task = scanner2.nextLine();
        homework.setTask(task);

        return homework;
    }

    public Homework createElementAuto() {
        homework = new Homework();
        Random random = new Random();
        int id = random.nextInt(1, 50);
        homework.setId(id);

        if (id < 10 || id > 40) {
            homework.setTask("Doing first and second");
        } else if (id < 20 || id > 30) {
            homework.setTask("Doing last");
        } else {
            homework.setTask("No homework!!!");
        }
        return homework;
    }

}
