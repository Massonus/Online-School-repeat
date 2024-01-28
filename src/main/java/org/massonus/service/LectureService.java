package org.massonus.service;

import org.massonus.entity.*;
import org.massonus.repo.CourseRepo;
import org.massonus.repo.LectureRepo;
import org.massonus.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LectureService implements UniversalService<Lecture> {
    private final LectureRepo lectureRepo;
    private final AdditionalMaterialService materialsService;
    private final HomeworkService homeworkService;
    private final PersonRepo personRepo;
    private final CourseRepo courseRepo;

    @Autowired
    public LectureService(LectureRepo lectureRepo, AdditionalMaterialService materialsService, HomeworkService homeworkService, PersonRepo personRepo, CourseRepo courseRepo) {
        this.lectureRepo = lectureRepo;
        this.materialsService = materialsService;
        this.homeworkService = homeworkService;
        this.personRepo = personRepo;
        this.courseRepo = courseRepo;
    }

    private Lecture lecture;

    public Lecture createElementByUser() {

        lecture = new Lecture();
        System.out.println("Now create the Lecture");
        System.out.println("Enter name of lecture");
        Scanner scanner1 = new Scanner(System.in);
        String name = scanner1.nextLine();
        lecture.setSubject(name);

        System.out.println("Enter description of lecture");
        Scanner scanner2 = new Scanner(System.in);
        String description = scanner2.nextLine();
        lecture.setDescription(description);

        System.out.println("Choose the course for lecture");
        courseRepo.findAll().forEach(System.out::println);
        Scanner scanner3 = new Scanner(System.in);
        Long courseId = scanner3.nextLong();
        Course courseById = courseRepo.getById(courseId);
        lecture.setCourse(courseById);

        System.out.println("Choose a teacher for Lecture" +
                "Enter the id");

        List<Person> allPeople = getAllTeachers();
        allPeople.forEach(System.out::println);

        Scanner scanner4 = new Scanner(System.in);
        Long personId = scanner4.nextLong();
        Person personForLecture = personRepo.findById(personId).get();
        lecture.setPerson(personForLecture);

        return lecture;
    }

    public Lecture createElementAuto(final Course course, final Person person) {
        lecture = new Lecture();
        long size = lectureRepo.findAll().size();
        long id = size + 1;

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
        lecture.setCourse(course);
        lecture.setPerson(person);
        lectureRepo.save(lecture);
        lecture.setMaterials(createAndFillMaterialsListForLecture(lecture));
        lecture.setHomeworks(createAndFillHomeworkListListForLecture(lecture));

        return lecture;
    }

    public Lecture lectureRefactor(final Lecture lecture) {
        System.out.println("Change name of lecture");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        lecture.setSubject(name);

        System.out.println("Change description of lecture");
        Scanner scanner1 = new Scanner(System.in);
        String description = scanner1.nextLine();
        lecture.setDescription(description);

        System.out.println("Change the course for lecture");
        courseRepo.findAll().forEach(System.out::println);
        Scanner scanner2 = new Scanner(System.in);
        Long courseId = scanner2.nextLong();
        Course courseById = courseRepo.getById(courseId);
        lecture.setCourse(courseById);

        System.out.println("Change a teacher for Lecture" +
                "Enter the id");
        Scanner scanner3 = new Scanner(System.in);

        List<Person> allTeachers = getAllTeachers();
        allTeachers.forEach(System.out::println);

        Long teacherId = scanner3.nextLong();
        Person personById = personRepo.getById(teacherId);
        lecture.setPerson(personById);

        return lecture;

    }

    public List<AdditionalMaterial> createAndFillMaterialsListForLecture(final Lecture lecture) {
        List<AdditionalMaterial> materials = new ArrayList<>();
        Random random = new Random();
        int lengthMas = random.nextInt(1, 10);
        for (int i = 0; i < lengthMas; i++) {
            AdditionalMaterial elementAuto = materialsService.createElementAuto();
            elementAuto.setLecture(lecture);
            materialsService.saveMaterial(elementAuto);
            materials.add(elementAuto);
        }
        return materials;
    }

    public List<Homework> createAndFillHomeworkListListForLecture(final Lecture lecture) {
        List<Homework> homeworkList = new ArrayList<>();
        Random random = new Random();
        int lengthMas = random.nextInt(1, 10);
        for (int i = 0; i < lengthMas; i++) {
            Homework elementAuto = homeworkService.createElementAuto();
            elementAuto.setLecture(lecture);
            homeworkService.saveHomework(elementAuto);
            homeworkList.add(elementAuto);
        }
        return homeworkList;
    }

    public Lecture getById(List<Lecture> list, long id) {

        List<Lecture> collect = list.stream()
                .filter(l -> l.getId().equals(id))
                .toList();

        return collect.get(0);
    }

    public void saveLecture(final Lecture lecture) {
        lectureRepo.save(lecture);
    }

    public List<Lecture> getLectureList() {
        return lectureRepo.findAll();
    }

    public Optional<Lecture> getLectureById(final long id) {
        return lectureRepo.findById(id);
    }

    public void deleteLecture(final long id) {
        lectureRepo.deleteById(id);
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

    public List<Lecture> sortLectureById(List<Lecture> lectures) {
        return lectures.stream()
                .sorted(Comparator.comparing(Lecture::getId))
                .collect(Collectors.toList());
    }

    public List<Person> getAllTeachers() {
        List<Person> allPeople = personRepo.findAll();
        return allPeople.stream()
                .filter(a -> a.getRole().toString().equals("TEACHER"))
                .collect(Collectors.toList());
    }
}
