package org.massonus.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.massonus.entity.Course;
import org.massonus.repo.CourseRepo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/courses")
public class CourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        final CourseRepo courseRepo = new CourseRepo();
        final List<Course> courses = courseRepo.getAllCourses();

        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Hello " + courses + "</h2>");

        writer.close();
    }
}
