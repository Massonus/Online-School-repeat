package org.massonus.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.Homework;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LectureRepo implements UniversalRepository {
    private static final Logger logger = LogManager.getLogger(LectureRepo.class);
    private final PersonRepo personRepo;
    private final AdditionalMaterialRepo materialsRepo;
    private final HomeworkRepo homeworkRepo;
    private Integer id;
    private Integer teacherId;

    @Autowired
    public LectureRepo(PersonRepo personRepo, AdditionalMaterialRepo materialsRepo, HomeworkRepo homeworkRepo) {
        this.personRepo = personRepo;
        this.materialsRepo = materialsRepo;
        this.homeworkRepo = homeworkRepo;

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            context.setConfigLocation(LectureRepo.class.getResource("/log4j.xml").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Lecture> getAllLectures() {
        try {
            final String sql = "SELECT * FROM lecture";
            try (Connection conn = createCon();
                 Statement statement = conn.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                List<Lecture> lectures = new ArrayList<>();

                while (resultSet.next()) {
                    Lecture lecture = new Lecture();
                    id = resultSet.getInt("id");
                    lecture.setId(id);
                    lecture.setSubject(resultSet.getString("subject"));
                    lecture.setDescription(resultSet.getString("description"));
                    teacherId = resultSet.getInt("teacher_id");
                    lecture.setTeacherId(teacherId);
                    lecture.setPerson(getPersonForLecture());
                    lecture.setHomeworks(getHomeworkListForLEcture());
                    lecture.setMaterials(getMaterialsListForLecture());
                    lecture.setCourseId(resultSet.getInt("course_id"));
                    lecture.setLectureDate(resultSet.getDate("lecture_date").toLocalDate());
                    lectures.add(lecture);
                }

                return lectures;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
        throw new IllegalArgumentException();
    }

    public List<Lecture> getSortedLectures() {
        try {
            final String sql = "SELECT subject, lecture_date, COUNT(task) FROM lecture INNER JOIN additional_material ON additional_material.lecture_id = lecture.id WHERE extract(year from lecture_date) < 2023 GROUP BY subject, lecture_date ORDER BY lecture_date";
            try (Connection conn = createCon();
                 Statement statement = conn.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                List<Lecture> lectures = new ArrayList<>();

                while (resultSet.next()) {
                    Lecture lecture = new Lecture();
                    lecture.setSubject(resultSet.getString("subject"));
                    lecture.setLectureDate(resultSet.getDate("lecture_date").toLocalDate());
                    lectures.add(lecture);
                }

                return lectures;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
        throw new IllegalArgumentException();
    }

    public Lecture getTheEarliestLecture() {
        try {
            final String sql = "SELECT lecture.id, subject, lecture_date, description, teacher_id, course_id, COUNT(task) AS count_materials FROM lecture INNER JOIN additional_material ON additional_material.lecture_id = lecture.id WHERE extract(year from lecture_date) < 2023 GROUP BY lecture.id, subject, lecture_date, description, teacher_id, course_id ORDER BY count_materials DESC, lecture_date LIMIT 1";
            try (Connection conn = createCon();
                 Statement statement = conn.createStatement()) {

                final ResultSet resultSet = statement.executeQuery(sql);

                List<Lecture> lectures = new ArrayList<>();

                while (resultSet.next()) {

                    Lecture lecture = new Lecture();
                    id = resultSet.getInt("id");
                    lecture.setId(id);
                    lecture.setSubject(resultSet.getString("subject"));
                    lecture.setLectureDate(resultSet.getDate("lecture_date").toLocalDate());
                    lecture.setDescription(resultSet.getString("description"));
                    lecture.setTeacherId(resultSet.getInt("teacher_id"));
                    lecture.setMaterials(getMaterialsListForLecture());
                    lecture.setHomeworks(getHomeworkListForLEcture());
                    lecture.setCourseId(resultSet.getInt("course_id"));
                    lectures.add(lecture);
                }
                System.out.println(lectures);
                return lectures.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
        throw new IllegalArgumentException();
    }

    public int delete(int id) {
        try {
            final String sql = "DELETE FROM public.lecture\n" +
                    "\tWHERE id= " + id;
            try (Connection conn = createCon();
                 Statement statement = conn.createStatement()) {
                final int i = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                return i;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
        throw new IllegalArgumentException();
    }

    private Person getPersonForLecture() {
        List<Person> list = personRepo.getAllTeachers().stream()
                .filter(p -> p.getId().equals(teacherId))
                .toList();
        return list.get(0);
    }

    private List<Homework> getHomeworkListForLEcture() {
        return homeworkRepo.getAllHomework().stream()
                .filter(h -> h.getLectureId().equals(id))
                .collect(Collectors.toList());
    }

    private List<AdditionalMaterial> getMaterialsListForLecture() {
        return materialsRepo.getAllMaterials().stream()
                .filter(m -> m.getLectureId().equals(id))
                .collect(Collectors.toList());
    }
}