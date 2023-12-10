package org.massonus.repo;

import org.massonus.entity.Homework;
import org.massonus.service.HomeworkService;

import java.util.*;

public class HomeworkRepo implements UniversalRepository {
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
        int lengthMas = lengthMasByUser();
        homeworkSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            Homework element = homeworkService.createElementByUser();
            element.setLectureId(lectureId);
            homeworkSet.add(element);
        }
        homeworks = new ArrayList<>(homeworkSet);
        return homeworks;
    }
}
