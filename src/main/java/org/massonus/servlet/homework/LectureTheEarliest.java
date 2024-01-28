package org.massonus.servlet.homework;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/lecture_earliest")
public class LectureTheEarliest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_oldExample.xml");
        /*final LectureRepo lectureRepo = context.getBean("lectureRepo", LectureRepo.class);*/

        resp.setContentType("text/html");

        /*final Lecture lecture = lectureRepo.getTheEarliestLecture();*/

        PrintWriter writer = resp.getWriter();
        /*writer.println("<h2>Hello " + lecture + "</h2>");*/

        writer.close();
    }
}
