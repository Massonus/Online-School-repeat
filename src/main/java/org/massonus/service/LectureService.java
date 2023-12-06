package org.massonus.service;

import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.log.Logger;
import org.massonus.repo.AdditionalMaterialsRepo;
import org.massonus.repo.HomeworkRepo;
import org.massonus.repo.PersonRepo;

import java.util.*;

public class LectureService {
    final HomeworkRepo homeworkRepo = new HomeworkRepo();
    final AdditionalMaterialsRepo materialsRepo = new AdditionalMaterialsRepo();
    final PersonService personService = new PersonService();
    final Logger logger = new Logger("LectureService");
    Lecture lecture;

    public Lecture createElementByUser() {
        lecture = new Lecture();
        System.out.println("Now create the Lecture");
        System.out.println("Enter id of lecture");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        lecture.setId(id);

        System.out.println("Enter name of lecture");
        Scanner scanner1 = new Scanner(System.in);
        String name = scanner1.nextLine();
        lecture.setName(name);

        System.out.println("Enter description of lecture");
        Scanner scanner2 = new Scanner(System.in);
        String description = scanner2.nextLine();
        lecture.setDescription(description);
        lecture.setCourseId(CourseService.courseId);
        homeworkRepo.createAndFillListByUser(id);
        materialsRepo.createAndFillListByUser(id);

        lecture.setHomeworks(HomeworkRepo.homeworks);
        lecture.setMaterials(AdditionalMaterialsRepo.materials);


        /*Person person = Optional.ofNullable(getPersonForLectureByUser())
                .orElseGet(personService::createElementByUser);

        lecture.setPerson(person);
        lecture.setPersonId(person.getId());*/

        return lecture;
    }

    public Lecture createElementAuto(List<Person> people) {
        lecture = new Lecture();
        Random random = new Random();
        int id = random.nextInt(1, 50);
        lecture.setId(id);

        if (id < 10 || id > 40) {
            lecture.setName("Math");
            lecture.setDescription("About Math");
        } else if (id < 20 || id > 30) {
            lecture.setName("Geography");
            lecture.setDescription("About Geography");
        } else {
            lecture.setName("English");
            lecture.setDescription("About English");
        }
        lecture.setCourseId(CourseService.courseId);

        homeworkRepo.createAndFillListAuto(id);
        materialsRepo.createAndFillListAuto(id);
        lecture.setHomeworks(HomeworkRepo.homeworks);
        lecture.setMaterials(AdditionalMaterialsRepo.materials);

        Person person = Optional.ofNullable(getPersonForLectureAuto(people))
                .orElseGet(personService::createElementAuto);

        lecture.setPerson(person);
        lecture.setPersonId(person.getId());

        return lecture;
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
}
