package org.massonus.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.massonus.SessionCreator;
import org.massonus.entity.Lecture;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LectureRepo implements UniversalRepository {

    private Integer id;

    public LectureRepo() {

    }

    public void addLecture(final Lecture lecture) {

        final SessionFactory sessionFactory = SessionCreator.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.save(lecture);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Lecture> getLectureList() {
        final SessionFactory sessionFactory = SessionCreator.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            final Query<Lecture> fromChild = session.createQuery("from Lecture", Lecture.class);
            return fromChild.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Lecture getLectureById(int id) {
        final SessionFactory sessionFactory = SessionCreator.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            final Query<Lecture> lectureQuery = session.createQuery("from Lecture where lecture_id = :id", Lecture.class);
            lectureQuery.setParameter("id", id);
            List<Lecture> list = lectureQuery.list();
            return list.get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Boolean updateLecture(final Lecture lecture) {
        try (final Session session = SessionCreator.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.update(lecture);
            transaction.commit();
            return true;
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean deleteLecture(final Lecture lecture) {
        try (final Session session = SessionCreator.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.delete(lecture);
            transaction.commit();
            return true;
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
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



    /*private Person getPersonForLecture() {
        List<Person> list = personRepo.getAllTeachers().stream()
                .filter(p -> p.getId().equals(teacherId))
                .toList();
        return list.get(0);
    }*/

    /*private List<Homework> getHomeworkListForLEcture() {
        return homeworkRepo.getAllHomework().stream()
                .filter(h -> h.getLectureId().equals(id))
                .collect(Collectors.toList());
    }*/

    /*private List<AdditionalMaterial> getMaterialsListForLecture() {
        return materialsRepo.getAllMaterials().stream()
                .filter(m -> m.getLectureId().equals(id))
                .collect(Collectors.toList());
    }*/
}