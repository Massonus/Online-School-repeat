package org.massonus.repo;

import org.massonus.entity.Person;
import org.massonus.entity.Role;
import org.massonus.log.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonRepo implements UniversalRepository {
    private final Logger logger = new Logger("LectureRepo");

    public List<Person> getAllPeople() {
        try {
            final String sql = "SELECT * FROM person";
            try (Connection conn = createCon();
                 Statement statement = conn.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                final List<Person> homeworkList = new ArrayList<>();

                while (resultSet.next()) {
                    Person person = new Person();
                    person.setId(resultSet.getInt("id"));
                    person.setFirstName(resultSet.getString("first_name"));
                    person.setLastName(resultSet.getString("last_name"));
                    person.setRole(resultSet.getString("role").equals("TEACHER") ? Role.TEACHER : Role.STUDENT);
                    person.setPhone(resultSet.getString("phone"));
                    person.setEmail(resultSet.getString("email"));
                    person.setCourseId(resultSet.getInt("course_id"));
                    homeworkList.add(person);
                }

                return homeworkList;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
        throw new IllegalArgumentException();
    }
}
