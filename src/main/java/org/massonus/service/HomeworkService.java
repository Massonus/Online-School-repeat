package org.massonus.service;

import org.massonus.entity.Homework;
import org.massonus.entity.Lecture;
import org.massonus.repo.HomeworkRepo;
import org.massonus.repo.LectureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

@Service
public class HomeworkService implements UniversalService<Homework> {


    private final HomeworkRepo homeworkRepo;
    private final LectureRepo lectureRepo;

    @Autowired
    public HomeworkService(HomeworkRepo homeworkRepo, LectureRepo lectureRepo) {
        this.homeworkRepo = homeworkRepo;
        this.lectureRepo = lectureRepo;
    }

    Homework homework;

    public Homework createElementByUserForm(final String task, final Long lectureId) {
        homework = new Homework();

        homework.setTask(task);
        Lecture lectureById = lectureRepo.findById(lectureId).orElse(null);
        homework.setLecture(lectureById);

        return homework;
    }

    public Homework createElementByUser() {
        homework = new Homework();

        System.out.println("Enter task of homework");
        Scanner scanner2 = new Scanner(System.in);
        String task = scanner2.nextLine();
        homework.setTask(task);

        System.out.println("Choose the lecture for the homework");
        lectureRepo.findAll().forEach(System.out::println);
        Scanner scanner3 = new Scanner(System.in);
        Long id = scanner3.nextLong();
        Lecture lectureById = lectureRepo.findById(id).get();
        homework.setLecture(lectureById);

        return homework;
    }

    Homework createElementAuto() {
        homework = new Homework();
        Random random = new Random();
        long id = random.nextInt(1, 50);

        if (id < 10 || id > 40) {
            homework.setTask("Doing first and second");
        } else if (id < 20 || id > 30) {
            homework.setTask("Doing last");
        } else {
            homework.setTask("No homework!!!");
        }
        return homework;
    }

    public Homework homeworkRefactor(final Homework homework) {
        System.out.println("Change the task");
        Scanner scanner = new Scanner(System.in);
        String task = scanner.nextLine();
        homework.setTask(task);

        System.out.println("Change the lecture for the homework");
        lectureRepo.findAll().forEach(System.out::println);
        Scanner scanner3 = new Scanner(System.in);
        Long id = scanner3.nextLong();
        Lecture lectureById = lectureRepo.findById(id).get();
        homework.setLecture(lectureById);

        return homework;

    }

    public void saveHomework(final Homework homework) {
        homeworkRepo.save(homework);
    }

    public List<Homework> getHomeworkList() {
        return homeworkRepo.findAll();
    }

    public Optional<Homework> getHomeworkById(final long id) {
        return homeworkRepo.findById(id);
    }

    public void deleteHomework(final long id) {
        homeworkRepo.deleteById(id);
    }

    /*public List<Homework> sortHomeworkByLectureId(List<Homework> homeworks) {
        return homeworks.stream()
                .sorted(Comparator.comparing(Homework::getLectureId))
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Homework>> groupHomeworksByLectureId(List<Homework> homeworks) {
        return homeworks.stream()
                .collect(Collectors.groupingBy(Homework::getLectureId));
    }*/
}
