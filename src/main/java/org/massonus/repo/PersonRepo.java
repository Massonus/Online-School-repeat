package org.massonus.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.massonus.SessionCreator;
import org.massonus.entity.Person;
import org.springframework.stereotype.Repository;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonRepo implements UniversalRepository {
    private static final Logger logger = LogManager.getLogger(PersonRepo.class);

    public PersonRepo() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            context.setConfigLocation(PersonRepo.class.getResource("/log4j.xml").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPerson(final Person person) {
        final SessionFactory sessionFactory = SessionCreator.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Person> getPeopleList() {
        final SessionFactory sessionFactory = SessionCreator.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            final Query<Person> fromChild = session.createQuery("from Person", Person.class);
            return fromChild.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Person getPersonById(int id) {
        final SessionFactory sessionFactory = SessionCreator.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            final Query<Person> personQuery = session.createQuery("from Person where person_id = :id", Person.class);
            personQuery.setParameter("id", id);
            List<Person> list = personQuery.list();
            return list.get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Boolean updatePerson(final Person person) {
        try (final Session session = SessionCreator.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.update(person);
            transaction.commit();
            return true;
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean deletePerson(final Person person) {
        try (final Session session = SessionCreator.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.delete(person);
            transaction.commit();
            return true;
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Person> getAllTeachers() {
        List<Person> allPeople = getPeopleList();
        return allPeople.stream()
                .filter(a -> a.getRole().toString().equals("TEACHER"))
                .collect(Collectors.toList());
    }
}
