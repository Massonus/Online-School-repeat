package org.massonus.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.massonus.entity.Course;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseRepo implements UniversalRepository {
    private final PersonRepo personRepo = new PersonRepo();
    private final LectureRepo lectureRepo = new LectureRepo();
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CourseRepo.class);

    public CourseRepo() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            context.setConfigLocation(CourseRepo.class.getResource("/log4j.xml").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer id;

    public List<Course> getAllCourses() {
        try {
            final String sql = "SELECT * FROM course";
            try (Connection conn = createCon();
                 Statement statement = conn.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                final List<Course> courses = new ArrayList<>();

                while (resultSet.next()) {
                    Course course = new Course();
                    id = resultSet.getInt("id");
                    course.setId(id);
                    course.setCourseName(resultSet.getString("course_name"));
                    course.setPeople(getPeopleForCourse());
                    course.setLectures(getLecturesForCourse());
                    courses.add(course);
                    logger.info("Course created {}", course);
                }

                return courses;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
        throw new IllegalArgumentException();
    }

    public List<Person> getPeopleForCourse() {
        return personRepo.getAllPeople().stream()
                .filter(p -> p.getCourseId().equals(id))
                .toList();
    }

    public List<Lecture> getLecturesForCourse() {
        return lectureRepo.getAllLectures().stream()
                .filter(l -> l.getCourseId().equals(id))
                .toList();
    }
}
