package org.massonus.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.entity.Role;
import org.massonus.service.LectureService;
import org.massonus.service.PersonService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/person_add")
public class PersonPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        final PersonService personService = context.getBean("personService", PersonService.class);
        response.setContentType("text/html");

        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        Role role = Role.valueOf(request.getParameter("role"));
        Integer course_id = Integer.valueOf(request.getParameter("course_id"));

        final Person person = new Person(first_name, last_name, phone, email, role, course_id);
        personService.add(person);

        try (PrintWriter writer = response.getWriter()) {
            writer.println("<p>First Name: " + first_name + "</p>");
            writer.println("<p>Last Name: " + last_name + "</p>");
            writer.println("<p>Phone: " + phone + "</p>");
            writer.println("<p>Email: " + email + "</p>");
            writer.println("<p>Role: " + role + "</p>");
            writer.println("<p>Course id: " + course_id + "</p>");
        }
    }
}
