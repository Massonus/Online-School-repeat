package org.massonus.repo;

import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.log.Logger;
import org.massonus.service.LectureService;

import java.util.*;

public class LectureRepo implements AboutRepo<Lecture> {

    private List<Lecture> lectures;
    private final LectureService lectureService = new LectureService();
    private final PersonRepo personRepo = new PersonRepo();
    private final Logger logger = new Logger("LectureRepo");

    public List<Lecture> createAndFillListAuto(List<Person> people) {
        Random random = new Random();
        int lengthMas = random.nextInt(1, 50);
        Set<Lecture> lectureSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            lectureSet.add(lectureService.createElementAuto(people));
        }
        lectures = new ArrayList<>(lectureSet);
        logger.info("List created successful, size : " + lengthMas);
        return lectures;
    }

    public void createAndFillListByUser() {
        personRepo.createAndFillListByUser();
        System.out.println("Lecture:");
        int lengthMas = lengthMas();
        for (int i = 0; i < lengthMas; i++) {
            lectures.add(lectureService.createElementByUser());
        }
    }

    public void add(List<Person> people) {
        if (choice().equals("2")) {
            Lecture elementAuto = lectureService.createElementAuto(people);
            logger.info("added: " + elementAuto);
            lectures.add(elementAuto);
        } else {
            Lecture elementByUser = lectureService.createElementByUser();
            logger.info("added: " + elementByUser);
            lectures.add(elementByUser);
        }
    }

    public void add(List<Person> people, int index) {
        if (choice().equals("2")) {
            Lecture elementAuto = lectureService.createElementAuto(people);
            logger.info("added: " + elementAuto);
            lectures.add(index, elementAuto);
        } else {
            Lecture elementByUser = lectureService.createElementByUser();
            logger.info("added: " + elementByUser);
            lectures.add(index, elementByUser);
        }
    }
}