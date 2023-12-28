package org.massonus.repo;

import lombok.Getter;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.log.Logger;
import org.massonus.service.LectureService;

import java.util.*;

public class LectureRepo implements UniversalRepository {

    @Getter
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
            Lecture elementAuto = lectureService.createElementAuto();
            Person person = Optional.ofNullable(getPersonForLectureAuto(people))
                    .orElse(new Person());

            elementAuto.setPerson(person);
            elementAuto.setTeacherId(person.getId());
            lectureSet.add(elementAuto);
        }
        lectures = new ArrayList<>(lectureSet);
        logger.info("List created successful, size : " + lengthMas);
        return lectures;
    }

    public List<Lecture> createAndFillListByUser(List<Person> people) {
        personRepo.getAllPeople();
        System.out.println("Lecture:");
        int lengthMas = lengthMasByUser();
        lectureSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            Lecture elementByUser = lectureService.createElementByUser();
            Person person = Optional.ofNullable(getPersonForLectureByUser(people))
                    .orElse(new Person());

            elementByUser.setPerson(person);
            elementByUser.setTeacherId(person.getId());
            lectureSet.add(elementByUser);
        }
        lectures = new ArrayList<>(lectureSet);
        logger.info("List created successful, size : " + lengthMas);
        return lectures;
    }

    public Person getPersonForLectureAuto(List<Person> people) {
        if (people == null) {
            return null;
        }
        int[] ints = new int[people.size()];
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            ints[i] = person.getId();
        }
        Arrays.sort(ints);
        Random random = new Random();
        int id;

        try {
            id = random.nextInt(ints[0], ints[ints.length - 1]);
        } catch (IllegalArgumentException e) {
            id = people.get(0).getId();
        }

        for (Person person : people) {
            if (person == null) {
                continue;
            }
            if (id == person.getId()) {
                return person;
            }
        }
        return getPersonForLectureAuto(people);
    }

    public Person getPersonForLectureByUser(List<Person> people) {
        System.out.println("Enter id of Person that will be in the Lecture");
        Scanner scanner = new Scanner(System.in);
        int id;
        try {
            id = scanner.nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            id = 0;
        }

        for (Person person : people) {
            if (id == person.getId()) {
                logger.info("person set " + person);
                return person;
            }
        }
        System.out.println("Incorrect id, try again");
        logger.warning("incorrect id " + id);
        for (Person person : people) {
            System.out.println(person);
        }
        return getPersonForLectureByUser(people);
    }
}