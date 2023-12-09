package org.massonus.repo;

import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.log.Logger;
import org.massonus.service.LectureService;

import java.util.*;

public class LectureRepo implements AboutRepo<Lecture> {

    private List<Lecture> lectures;
    private Set<Lecture> lectureSet;
    private final LectureService lectureService = new LectureService();
    private final PersonRepo personRepo = new PersonRepo();
    private final Logger logger = new Logger("LectureRepo");

    public List<Lecture> createAndFillListAuto(List<Person> people) {
        Random random = new Random();
        int lengthMas = random.nextInt(1, 50);
        lectureSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            lectureSet.add(lectureService.createElementAuto(people));
        }
        lectures = new ArrayList<>(lectureSet);
        logger.info("List created successful, size : " + lengthMas);
        return lectures;
    }

    public List<Lecture> createAndFillListByUser(List<Person> people) {
        personRepo.createAndFillListByUser();
        System.out.println("Lecture:");
        int lengthMas = lengthMas();
        lectureSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            lectureSet.add(lectureService.createElementByUser(people));
        }
        lectures = new ArrayList<>(lectureSet);
        logger.info("List created successful, size : " + lengthMas);
        return lectures;
    }

    public void add(List<Person> people) {
        if (choice().equals("2")) {
            Lecture elementAuto = lectureService.createElementAuto(people);
            logger.info("added: " + elementAuto);
            lectures.add(elementAuto);
        } else {
            Lecture elementByUser = lectureService.createElementByUser(people);
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
            Lecture elementByUser = lectureService.createElementByUser(people);
            logger.info("added: " + elementByUser);
            lectures.add(index, elementByUser);
        }
    }
}