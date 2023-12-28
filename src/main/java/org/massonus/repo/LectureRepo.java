package org.massonus.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.Homework;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LectureRepo implements UniversalRepository {
    private final PersonRepo personRepo = new PersonRepo();
    private final AdditionalMaterialsRepo materialsRepo = new AdditionalMaterialsRepo();
    private final HomeworkRepo homeworkRepo = new HomeworkRepo();
    private static final Logger logger = LogManager.getLogger(LectureRepo.class);
    private Integer id;
    private Integer teacherId;

    public LectureRepo() {
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

                final List<Lecture> lectures = new ArrayList<>();

                while (resultSet.next()) {
                    Lecture lecture = new Lecture();
                    id = resultSet.getInt("id");
                    lecture.setId(id);
                    lecture.setSubject(resultSet.getString("subject"));
                    lecture.setDescription(resultSet.getString("description"));
                    teacherId = resultSet.getInt("teacher_id");
                    lecture.setTeacherId(teacherId);
                    lecture.setPerson(getPersonForLecture());
                    lecture.setHomeworks(getHomeworkList());
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

    public Person getPersonForLecture() {
        List<Person> list = personRepo.getAllTeachers().stream()
                .filter(p -> p.getId().equals(teacherId))
                .toList();
        return list.get(0);
    }

    public List<Homework> getHomeworkList() {
        return homeworkRepo.getAllHomework().stream()
                .filter(h -> h.getLectureId().equals(id))
                .toList();
    }

    public List<AdditionalMaterial> getMaterialsListForLecture() {
        return materialsRepo.getAllMaterials().stream()
                .filter(m -> m.getLectureId().equals(id))
                .toList();
    }
}