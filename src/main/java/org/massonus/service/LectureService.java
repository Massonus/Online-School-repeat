package org.massonus.service;

import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.log.Logger;
import org.massonus.repo.AdditionalMaterialsRepo;
import org.massonus.repo.HomeworkRepo;

import java.util.*;
import java.util.stream.Collectors;

public class LectureService implements UniversalService<Lecture> {
    private final HomeworkRepo homeworkRepo = new HomeworkRepo();
    private final AdditionalMaterialsRepo materialsRepo = new AdditionalMaterialsRepo();
    private final PersonService personService = new PersonService();
    private final Logger logger = new Logger("LectureService");
    private Lecture lecture;

    public Lecture createElementByUser(List<Person> people) {
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

        lecture.setHomeworks(homeworkRepo.createAndFillListByUser(id));
        lecture.setMaterials(materialsRepo.createAndFillListByUser(id));


        Person person = Optional.ofNullable(getPersonForLectureByUser(people))
                .orElseGet(personService::createElementByUser);

        lecture.setPerson(person);
        lecture.setPersonId(person.getId());

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

        lecture.setHomeworks(homeworkRepo.createAndFillListAuto(id));
        lecture.setMaterials(materialsRepo.createAndFillListAuto(id));

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

    public boolean add(List<Lecture> lectures, List<Person> people) {
        if (choice().equals("2")) {
            Lecture elementAuto = createElementAuto(people);
            logger.info("added: " + elementAuto);
            lectures.add(elementAuto);
            return true;
        } else if (choice().equals("1")) {
            Lecture elementByUser = createElementByUser(people);
            logger.info("added: " + elementByUser);
            lectures.add(elementByUser);
            return true;
        }
        return false;
    }

    public boolean add(List<Lecture> lectures, List<Person> people, int index) {
        if (choice().equals("2")) {
            Lecture elementAuto = createElementAuto(people);
            logger.info("added: " + elementAuto);
            lectures.add(index, elementAuto);
            return true;
        } else if (choice().equals("1")) {
            Lecture elementByUser = createElementByUser(people);
            logger.info("added: " + elementByUser);
            lectures.add(index, elementByUser);
            return true;
        }
        return false;
    }

    public Lecture findFirstLecture(List<Lecture> lectures) {
        return lectures.stream()
                .max(Lecture::compareTo)
                .orElseGet(Lecture::new);
    }

    public Map<Person, List<Lecture>> groupLectureByPerson(List<Lecture> lectures) {
        return lectures.stream()
                .collect(Collectors.groupingBy(Lecture::getPerson));
    }
}
