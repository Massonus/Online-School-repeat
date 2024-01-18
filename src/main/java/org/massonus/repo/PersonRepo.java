package org.massonus.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.massonus.entity.Person;
import org.massonus.entity.Role;
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

    public List<Person> getAllPeople() {
        try {
            final String sql = "SELECT * FROM person";
            try (Connection conn = createCon();
                 Statement statement = conn.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                List<Person> people = new ArrayList<>();

                while (resultSet.next()) {
                    Person person = new Person();
                    person.setId(resultSet.getInt("id"));
                    person.setFirstName(resultSet.getString("first_name"));
                    person.setLastName(resultSet.getString("last_name"));
                    person.setRole(resultSet.getString("role").equals("TEACHER") ? Role.TEACHER : Role.STUDENT);
                    person.setPhone(resultSet.getString("phone"));
                    person.setEmail(resultSet.getString("email"));
                    person.setCourseId(resultSet.getInt("course_id"));
                    people.add(person);
                }

                return people;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
        throw new IllegalArgumentException();
    }

    public List<Person> getAllTeachers() {
        List<Person> allPeople = getAllPeople();
        return allPeople.stream()
                .filter(a -> a.getRole().toString().equals("TEACHER"))
                .collect(Collectors.toList());
    }
}
