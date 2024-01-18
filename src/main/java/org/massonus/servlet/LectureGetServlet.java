package org.massonus.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.massonus.entity.Lecture;
import org.massonus.repo.LectureRepo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/lectures")
public class LectureGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_oldExample.xml");
        final LectureRepo lectureRepo = context.getBean("lectureRepo", LectureRepo.class);
        final List<Lecture> lectures = lectureRepo.getAllLectures();

        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Hello " + lectures + "</h2>");

        writer.close();
    }
}
