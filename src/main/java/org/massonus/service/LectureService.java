package org.massonus.service;

import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.log.Logger;
import org.massonus.repo.AdditionalMaterialsRepo;
import org.massonus.repo.HomeworkRepo;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LectureService implements UniversalService<Lecture> {
    private final HomeworkRepo homeworkRepo = new HomeworkRepo();
    private final AdditionalMaterialsRepo materialsRepo = new AdditionalMaterialsRepo();
    private final Logger logger = new Logger("LectureService");
    private Lecture lecture;

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
        lecture.setSubject(name);

        System.out.println("Enter description of lecture");
        Scanner scanner2 = new Scanner(System.in);
        String description = scanner2.nextLine();
        lecture.setDescription(description);
        lecture.setCourseId(CourseService.courseId);

        lecture.setHomeworks(homeworkRepo.gatAllHomework());
        lecture.setMaterials(materialsRepo.gatAllMaterials());

        return lecture;
    }

    public Lecture createElementAuto() {
        lecture = new Lecture();
        Random random = new Random();
        int id = random.nextInt(1, 50);
        lecture.setId(id);

        if (id < 10 || id > 40) {
            lecture.setSubject("Math");
            lecture.setDescription("About Math");
        } else if (id < 20 || id > 30) {
            lecture.setSubject("Geography");
            lecture.setDescription("About Geography");
        } else {
            lecture.setSubject("English");
            lecture.setDescription("About English");
        }
        lecture.setCourseId(CourseService.courseId);

        lecture.setHomeworks(homeworkRepo.gatAllHomework());
        lecture.setMaterials(materialsRepo.gatAllMaterials());

        return lecture;
    }

    public boolean add(List<Lecture> lectures, String mode) {
        if (mode.equals("2")) {
            Lecture elementAuto = createElementAuto();
            logger.info("added: " + elementAuto);
            lectures.add(elementAuto);
            return true;
        } else if (mode.equals("1")) {
            Lecture elementByUser = createElementByUser();
            logger.info("added: " + elementByUser);
            lectures.add(elementByUser);
            return true;
        }
        return false;
    }

    public boolean add(List<Lecture> lectures, int index, String mode) {
        if (mode.equals("2")) {
            Lecture elementAuto = createElementAuto();
            logger.info("added: " + elementAuto);
            lectures.add(index, elementAuto);
            return true;
        } else if (mode.equals("1")) {
            Lecture elementByUser = createElementByUser();
            logger.info("added: " + elementByUser);
            lectures.add(index, elementByUser);
            return true;
        }
        return false;
    }

    public boolean removeById(List<Lecture> list, int id) {
        if (list == null) {
            System.out.println("Please create the List");
            logger.warning("array is empty");
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            Lecture element = list.get(i);
            if (id == element.getId()) {
                System.out.println(list.get(i));
                Lecture remove = list.remove(i);
                logger.info("element removed " + remove);
                return true;
            }
        }
        return false;
    }

    public Lecture getById(List<Lecture> list, int id) {
        if (list == null) {
            System.out.println("Please create an Array");
            return null;
        }

        for (Lecture element : list) {
            if (id == element.getId()) {
                return element;
            }
        }
        return null;
    }

    public Lecture findFirstLecture(List<Lecture> lectures) {
        return lectures.stream()
                .max(Lecture::compareTo)
                .orElseThrow();
    }

    public Map<Person, List<Lecture>> groupLectureByPerson(List<Lecture> lectures) {
        return lectures.stream()
                .collect(Collectors.groupingBy(Lecture::getPerson));
    }
}
