package org.massonus.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.massonus.SessionCreator;
import org.massonus.entity.Course;
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
public class CourseRepo implements UniversalRepository {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CourseRepo.class);
    private final PersonRepo personRepo;
    private final LectureRepo lectureRepo;

    @Autowired
    public CourseRepo(PersonRepo personRepo, LectureRepo lectureRepo) {
        this.personRepo = personRepo;
        this.lectureRepo = lectureRepo;

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

                List<Course> courses = new ArrayList<>();

                while (resultSet.next()) {
                    Course course = new Course();
                    id = resultSet.getInt("id");
                    course.setId(id);
                    course.setCourseName(resultSet.getString("course_name"));
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

    public static Boolean saveCourse(final Course course) {
        try (final Session session = SessionCreator.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
            return true;
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /*private List<Person> getPeopleForCourse() {
        return personRepo.getAllPeople().stream()
                .filter(p -> p.getCourseId().equals(id))
                .collect(Collectors.toList());
    }*/

    /*private List<Lecture> getLecturesForCourse() {
        return lectureRepo.getAllLectures().stream()
                .filter(l -> l.getCourseId().equals(id))
                .collect(Collectors.toList());
    }*/
}
