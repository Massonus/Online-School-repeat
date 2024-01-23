package org.massonus.service;

import org.massonus.entity.Homework;
import org.massonus.repo.UniversalRepository;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Scanner;

@Service
public class HomeworkService implements UniversalService<Homework>, UniversalRepository {

    public HomeworkService() {
    }

    Homework homework;

    public Homework createElementByUser() {
        homework = new Homework();

        System.out.println("Enter task of homework");
        Scanner scanner2 = new Scanner(System.in);
        String task = scanner2.nextLine();
        homework.setTask(task);

        return homework;
    }

    Homework createElementAuto() {
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

    public Homework homeworkRefactor(final Homework homework) {
        System.out.println("Change the task");
        Scanner scanner = new Scanner(System.in);
        String task = scanner.nextLine();
        homework.setTask(task);

        return homework;

    }

    /*public List<Homework> sortHomeworkByLectureId(List<Homework> homeworks) {
        return homeworks.stream()
                .sorted(Comparator.comparing(Homework::getLectureId))
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Homework>> groupHomeworksByLectureId(List<Homework> homeworks) {
        return homeworks.stream()
                .collect(Collectors.groupingBy(Homework::getLectureId));
    }*/
}
