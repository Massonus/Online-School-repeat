package org.massonus.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.massonus.entity.Lecture;
import org.massonus.repo.LectureRepo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/lectures")
public class LectureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        final LectureRepo lectureRepo = new LectureRepo();
        final List<Lecture> lectures = lectureRepo.getAllLectures();

        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Hello " + lectures + "</h2>");

        writer.close();
    }
}
