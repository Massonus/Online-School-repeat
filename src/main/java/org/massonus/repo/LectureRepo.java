package org.massonus.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.massonus.SessionCreator;
import org.massonus.entity.Lecture;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LectureRepo implements UniversalRepository {

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