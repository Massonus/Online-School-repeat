package org.massonus.service;

import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.log.Logger;
import org.massonus.repo.LectureRepo;
import org.massonus.repo.UniversalRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

public class LectureService implements UniversalService<Lecture>, UniversalRepository {
    private final Logger logger = new Logger("LectureService");
    private final LectureRepo lectureRepo;

    public LectureService(LectureRepo lectureRepo) {
        this.lectureRepo = lectureRepo;
    }

    private final PersonService personService = new PersonService();
    private final AdditionalMaterialsService materialsService = new AdditionalMaterialsService();
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
        lecture.setSubject(name);

        System.out.println("Enter description of lecture");
        Scanner scanner2 = new Scanner(System.in);
        String description = scanner2.nextLine();
        lecture.setDescription(description);

        System.out.println("Choose a teacher for Lecture" +
                "Enter the id");
        people.forEach(System.out::println);
        ;
        Scanner scanner3 = new Scanner(System.in);
        int personId = scanner3.nextInt();
        lecture.setPerson(personService.getById(people, personId));
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

    public List<AdditionalMaterial> createAndFillMaterialsListForLecture(Integer lectureId) {
        List<AdditionalMaterial> materials = new ArrayList<>();
        System.out.println("Additional material:");
        int lengthMas = lengthMasByUser();
        for (int i = 0; i < lengthMas; i++) {
            materialsService.add(materials, lectureId);
        }
        return materials;
    }

    public boolean add(Lecture elementByUser, List<Lecture> lectures, Integer courseId) {
        elementByUser.setCourseId(courseId);
        insertLectureIntoDatabase(elementByUser);
        logger.info("added: " + elementByUser);
        return lectures.add(elementByUser);
    }

    public void add(Lecture lecture) {
        int size = lectureRepo.getAllLectures().size();
        lecture.setId(size + 2);
        insertLectureIntoDatabase(lecture);
    }

    public boolean removeById(List<Lecture> list, int id) {
        for (int i = 0; i < list.size(); i++) {
            Lecture element = list.get(i);
            if (id == element.getId()) {
                System.out.println(list.get(i));
                Lecture remove = list.remove(i);
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
}
