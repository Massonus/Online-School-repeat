package org.massonus.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.massonus.entity.Person;
import org.massonus.repo.PersonRepo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/people")
public class PersonGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        final PersonRepo personRepo = new PersonRepo();
        final List<Person> people = personRepo.getAllPeople();

        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Hello " + people + "</h2>");

        writer.close();
    }
}
