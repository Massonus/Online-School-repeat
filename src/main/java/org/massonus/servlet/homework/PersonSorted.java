package org.massonus.servlet.homework;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.massonus.entity.Person;
import org.massonus.entity.Role;
import org.massonus.repo.UniversalRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/people_sorted")
public class PersonSorted extends HttpServlet implements UniversalRepository {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        final List<Person> people = getSortedPeople();

        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Hello " + people + "</h2>");

        writer.close();
    }

    public List<Person> getSortedPeople() {
        try {
            final String sql = "SELECT * FROM person WHERE role='STUDENT' ORDER BY last_name ";
            try (Connection conn = createCon();
                 Statement statement = conn.createStatement()) {
                final ResultSet resultSet = statement.executeQuery(sql);

                List<Person> people = new ArrayList<>();

                while (resultSet.next()) {
                    Person person = new Person();
                    /*person.setId(resultSet.getInt("id"));*/
                    person.setFirstName(resultSet.getString("first_name"));
                    person.setLastName(resultSet.getString("last_name"));
                    person.setRole(resultSet.getString("role").equals("TEACHER") ? Role.TEACHER : Role.STUDENT);
                    person.setPhone(resultSet.getString("phone"));
                    person.setEmail(resultSet.getString("email"));
                    people.add(person);
                }

                return people;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
        throw new IllegalArgumentException();
    }

}
