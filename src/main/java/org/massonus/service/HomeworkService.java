package org.massonus.service;

import org.massonus.entity.Homework;

import java.util.*;
import java.util.stream.Collectors;

public class HomeworkService implements UniversalService<Homework> {
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

    public boolean add(List<Homework> homeworks) {
        if (choice().equals("2")) {
            Homework elementAuto = createElementAuto();
            logger.info("added: " + elementAuto);
            homeworks.add(elementAuto);
            return true;
        } else if (choice().equals("1")) {
            Homework elementByUser = createElementByUser();
            logger.info("added: " + elementByUser);
            homeworks.add(elementByUser);
            return true;
        }
        return false;
    }

    public boolean add(List<Homework> homeworks, int index) {
        if (choice().equals("2")) {
            Homework elementAuto = createElementAuto();
            logger.info("added: " + elementAuto);
            homeworks.add(index, elementAuto);
            return true;
        } else if (choice().equals("1")) {
            Homework elementByUser = createElementByUser();
            logger.info("added: " + elementByUser);
            homeworks.add(index, elementByUser);
            return true;
        }
        return false;
    }

    public boolean removeById(List<Homework> list, int id) {
        if (list == null) {
            System.out.println("Please create the List");
            logger.warning("array is empty");
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            Homework element = list.get(i);
            if (id == element.getId()) {
                System.out.println(list.get(i));
                Homework remove = list.remove(i);
                logger.info("element removed " + remove);
                return true;
            }
        }
        return false;
    }

    public Homework getById(List<Homework> list, int id) {
        if (list == null) {
            System.out.println("Please create an Array");
            return null;
        }

        for (Homework element : list) {
            if (id == element.getId()) {
                return element;
            }
        }
        return null;
    }

    public List<Homework> sortHomeworkByLectureId(List<Homework> homeworks) {
        return homeworks.stream()
                .sorted(Comparator.comparing(Homework::getLectureId))
                .toList();
    }

    public Map<Integer, List<Homework>> groupHomeworksByLectureId(List<Homework> homeworks) {
        return homeworks.stream()
                .collect(Collectors.groupingBy(Homework::getLectureId));
    }
}
