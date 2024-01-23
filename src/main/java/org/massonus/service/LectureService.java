package org.massonus.service;

import org.massonus.entity.*;
import org.massonus.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LectureService implements UniversalService<Lecture>, UniversalRepository {
    private final LectureRepo lectureRepo;
    private final AdditionalMaterialService materialsService;
    private final AdditionalMaterialRepo materialRepo;
    private final HomeworkService homeworkService;
    private final HomeworkRepo homeworkRepo;
    private final PersonRepo personRepo;

    @Autowired
    public LectureService(LectureRepo lectureRepo, AdditionalMaterialService materialsService, AdditionalMaterialRepo materialRepo, HomeworkService homeworkService, HomeworkRepo homeworkRepo, PersonRepo personRepo) {
        this.lectureRepo = lectureRepo;
        this.materialsService = materialsService;
        this.materialRepo = materialRepo;
        this.homeworkService = homeworkService;
        this.homeworkRepo = homeworkRepo;
        this.personRepo = personRepo;
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

        System.out.println("Choose a teacher for Lecture" +
                "Enter the id");

        List<Person> allTeachers = personRepo.getAllTeachers();
        allTeachers.forEach(System.out::println);

        Scanner scanner3 = new Scanner(System.in);
        int personId = scanner3.nextInt();
        /*Person personForLecture = personService.getById(allPeople, personId);*/
        /*lecture.setPerson(personForLecture);*/

        return lecture;
    }

    public Lecture createElementAuto(final Course course, final Person person) {
        lecture = new Lecture();
        int size = lectureRepo.getLectureList().size();
        int id = size + 1;
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
        lecture.setCourse(course);
        lecture.setPerson(person);
        lectureRepo.addLecture(lecture);
        lecture.setMaterials(createAndFillMaterialsListForLecture(lecture));
        lecture.setHomeworks(createAndFillHomeworkListListForLecture(lecture));

        return lecture;
    }

    public List<AdditionalMaterial> createAndFillMaterialsListForLecture(final Lecture lecture) {
        List<AdditionalMaterial> materials = new ArrayList<>();
        Random random = new Random();
        int lengthMas = random.nextInt(1, 10);
        for (int i = 0; i < lengthMas; i++) {
            AdditionalMaterial elementAuto = materialsService.createElementAuto();
            elementAuto.setLecture(lecture);
            materialRepo.addMaterial(elementAuto);
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
            homeworkRepo.addHomework(elementAuto);
            homeworkList.add(elementAuto);
        }
        return homeworkList;
    }

    public Lecture getById(List<Lecture> list, int id) {

        List<Lecture> collect = list.stream()
                .filter(l -> l.getId().equals(id))
                .toList();

        return collect.get(0);
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
}
