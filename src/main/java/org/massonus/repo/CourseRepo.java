package org.massonus.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.massonus.SessionCreator;
import org.massonus.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepo implements UniversalRepository {

    public CourseRepo() {
    }

    public void addCourse(final Course course) {
        final SessionFactory sessionFactory = SessionCreator.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Course> getCourseList() {
        final SessionFactory sessionFactory = SessionCreator.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            final Query<Course> fromChild = session.createQuery("from Course", Course.class);
            return fromChild.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Course getCourseById(int id) {
        final SessionFactory sessionFactory = SessionCreator.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            final Query<Course> courseQuery = session.createQuery("from Course where course_id = :id", Course.class);
            courseQuery.setParameter("id", id);
            List<Course> list = courseQuery.list();
            return list.get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Boolean updateCourse(final Course course) {
        try (final Session session = SessionCreator.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
            return true;
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean deleteCourse(final Course course) {
        try (final Session session = SessionCreator.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.delete(course);
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
