package org.massonus.repo;

import org.massonus.entity.Lecture;
import org.massonus.log.Logger;
import org.massonus.service.LectureService;

import java.util.*;

public class LectureRepo implements AboutRepo<Lecture> {

    Set<Lecture> lectureSet;

    public static List<Lecture> lectures;
    final LectureService lectureService = new LectureService();
    final PersonRepo personRepo = new PersonRepo();
    final Logger logger = new Logger("LectureRepo");

    public void createAndFillListAuto() {
        personRepo.createAndFillListAuto();
        Random random = new Random();
        int lengthMas = random.nextInt(1, 50);
        lectureSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            lectureSet.add(lectureService.createElementAuto());
        }
        lectures = new ArrayList<>(lectureSet);
        logger.info("List created successful, size : " + lengthMas);
    }

    public void createAndFillListByUser() {
        personRepo.createAndFillListByUser();
        System.out.println("Lecture:");
        int lengthMas = lengthMas();
        for (int i = 0; i < lengthMas; i++) {
            lectures.add(lectureService.createElementByUser());
        }
    }

    public void add() {
        if (choice().equals("2")) {
            Lecture elementAuto = lectureService.createElementAuto();
            logger.info("added: " + elementAuto);
            lectures.add(elementAuto);
        } else {
            Lecture elementByUser = lectureService.createElementByUser();
            logger.info("added: " + elementByUser);
            lectures.add(elementByUser);
        }
    }

    public void add(int index) {
        if (choice().equals("2")) {
            Lecture elementAuto = lectureService.createElementAuto();
            logger.info("added: " + elementAuto);
            lectures.add(index, elementAuto);
        } else {
            Lecture elementByUser = lectureService.createElementByUser();
            logger.info("added: " + elementByUser);
            lectures.add(index, elementByUser);
        }
    }
}