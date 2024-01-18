package org.massonus.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.Homework;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.repo.LectureRepo;
import org.massonus.repo.PersonRepo;
import org.massonus.repo.UniversalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LectureService implements UniversalService<Lecture>, UniversalRepository {
    private static final Logger logger = LogManager.getLogger(LectureService.class);
    private final LectureRepo lectureRepo;
    private final AdditionalMaterialService materialsService;
    private final HomeworkService homeworkService;
    private final PersonService personService;

    @Autowired
    public LectureService(LectureRepo lectureRepo, AdditionalMaterialService materialsService, HomeworkService homeworkService, PersonService personService) {
        this.lectureRepo = lectureRepo;
        this.materialsService = materialsService;
        this.homeworkService = homeworkService;
        this.personService = personService;

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            context.setConfigLocation(LectureService.class.getResource("/log4j.xml").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private final PersonRepo personRepo = new PersonRepo();

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

        System.out.println("Enter course id");
        Scanner scanner = new Scanner(System.in);
        int courseId = scanner.nextInt();
        lecture.setCourseId(courseId);

        System.out.println("Choose a teacher for Lecture" +
                "Enter the id");

        List<Person> allPeople = personRepo.getAllTeachers();
        allPeople.stream()
                .filter(a -> a.getCourseId().equals(courseId))
                .forEach(System.out::println);

        Scanner scanner3 = new Scanner(System.in);
        int personId = scanner3.nextInt();
        Person personForLecture = personService.getById(allPeople, personId);
        lecture.setPerson(personForLecture);
        lecture.setTeacherId(personId);


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

        return lecture;
    }

    private List<AdditionalMaterial> createAndFillMaterialsListForLecture(Integer lectureId) {
        List<AdditionalMaterial> materials = new ArrayList<>();
        System.out.println("Additional material:");
        int lengthMas = lengthMasByUser();
        for (int i = 0; i < lengthMas; i++) {
            materialsService.add(materials, lectureId);
        }
        return materials;
    }

    private List<Homework> createAndFillHomeworkListForLecture(Integer lectureId) {
        List<Homework> homeworkList = new ArrayList<>();
        System.out.println("Homework:");
        int lengthMas = lengthMasByUser();
        for (int i = 0; i < lengthMas; i++) {
            homeworkService.add(homeworkList, lectureId);
        }
        return homeworkList;
    }

    public boolean add(Lecture elementByUser, List<Lecture> lectures) {
        int size = lectures.size();
        lecture.setId(size + 1);
        insertLectureIntoDatabase(elementByUser);
        List<AdditionalMaterial> materialsListForLecture = createAndFillMaterialsListForLecture(elementByUser.getId());
        List<Homework> homeworkListForLecture = createAndFillHomeworkListForLecture(elementByUser.getId());
        elementByUser.setMaterials(materialsListForLecture);
        elementByUser.setHomeworks(homeworkListForLecture);
        logger.info("added: " + elementByUser);
        return lectures.add(elementByUser);
    }

    public void add(Lecture lecture) {
        int size = lectureRepo.getAllLectures().size();
        lecture.setId(size + 1);
        insertLectureIntoDatabase(lecture);
    }

    public boolean removeById(List<Lecture> list, int id) {
        for (int i = 0; i < list.size(); i++) {
            Lecture element = list.get(i);
            if (id == element.getId()) {
                System.out.println(list.get(i));
                Lecture remove = list.remove(i);
                materialsService.deleteMaterialFromDatabaseByLectureId(remove.getId());
                homeworkService.deleteHomeworkFromDatabaseByLectureId(remove.getId());
                deleteLectureFromDatabase(id);
                logger.info("element removed " + remove);
                return true;
            }
        }
        return false;
    }

    private void insertLectureIntoDatabase(final Lecture lecture) {
        try {

            String sql = "INSERT INTO public.lecture(id, subject, description, teacher_id, course_id)VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                preparedStatement.setInt(1, lecture.getId());
                preparedStatement.setString(2, lecture.getSubject());
                preparedStatement.setString(3, lecture.getDescription());
                preparedStatement.setInt(4, lecture.getTeacherId());
                preparedStatement.setInt(5, lecture.getCourseId());


                int rows = preparedStatement.executeUpdate();
                System.out.println("add Lines Lecture: " + rows);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    private void deleteLectureFromDatabase(int id) {
        try {
            final String sql = "DELETE FROM public.lecture WHERE id=" + id;
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
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

    public List<Lecture> sortLectureById(List<Lecture> lectures) {
        return lectures.stream()
                .sorted(Comparator.comparing(Lecture::getId))
                .collect(Collectors.toList());
    }
}
