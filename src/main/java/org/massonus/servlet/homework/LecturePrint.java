package org.massonus.servlet.homework;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.massonus.entity.Lecture;
import org.massonus.repo.LectureRepo;
import org.massonus.repo.UniversalRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/lecture_print")
public class LecturePrint extends HttpServlet implements UniversalRepository {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final LectureRepo lectureRepo = new LectureRepo();

        resp.setContentType("text/html");

        final List<Lecture> lectures = lectureRepo.getSortedLectures();

        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Hello " + lectures + "</h2>");

        writer.close();
    }

}
