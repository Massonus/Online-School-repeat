package org.massonus.servlet.homework;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/lecture_print")
public class LecturePrint extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_oldExample.xml");
        /*final LectureRepo lectureRepo = context.getBean("lectureRepo", LectureRepo.class);*/

        resp.setContentType("text/html");

        /*final List<Lecture> lectures = lectureRepo.getSortedLectures();*/

        PrintWriter writer = resp.getWriter();
        /*writer.println("<h2>Hello " + lectures + "</h2>");*/

        writer.close();
    }

}
