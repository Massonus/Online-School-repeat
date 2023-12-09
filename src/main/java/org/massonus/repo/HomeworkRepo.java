package org.massonus.repo;

import org.massonus.entity.Homework;
import org.massonus.service.HomeworkService;

import java.util.*;
import java.util.stream.Collectors;

public class HomeworkRepo implements AboutRepo<Homework> {
    final HomeworkService homeworkService = new HomeworkService();
    private List<Homework> homeworks;
    private Set<Homework> homeworkSet;

    public List<Homework> createAndFillListAuto(int lectureId) {
        Random random = new Random();
        int lengthMas = random.nextInt(1, 30);
        homeworkSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            Homework element = homeworkService.createElementAuto();
            element.setLectureId(lectureId);
            homeworkSet.add(element);
        }
        homeworks = new ArrayList<>(homeworkSet);
        return homeworks;
    }

    public List<Homework> createAndFillListByUser(int lectureId) {
        System.out.println("Homework:");
        int lengthMas = lengthMas();
        homeworkSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            Homework element = homeworkService.createElementByUser();
            element.setLectureId(lectureId);
            homeworkSet.add(element);
        }
        homeworks = new ArrayList<>(homeworkSet);
        return homeworks;
    }

    public void add() {
        if (choice().equals("2")) {
            Homework elementAuto = homeworkService.createElementAuto();
            logger.info("added: " + elementAuto);
            homeworks.add(elementAuto);
        } else {
            Homework elementByUser = homeworkService.createElementByUser();
            logger.info("added: " + elementByUser);
            homeworks.add(elementByUser);
        }
    }

    public void add(int index) {
        if (choice().equals("2")) {
            Homework elementAuto = homeworkService.createElementAuto();
            logger.info("added: " + elementAuto);
            homeworks.add(index, elementAuto);
        } else {
            Homework elementByUser = homeworkService.createElementByUser();
            logger.info("added: " + elementByUser);
            homeworks.add(index, elementByUser);
        }
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
