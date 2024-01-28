package org.massonus.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.massonus.entity.Course;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/courses")
public class CourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_oldExample.xml");
        final CourseRepo courseRepo = context.getBean("courseRepo", CourseRepo.class);
        final List<Course> courses = courseRepo.getCourseList();
*/
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Hello " + /*courses +*/ "</h2>");

        writer.close();
    }
}
