package org.massonus.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.massonus.entity.Lecture;
import org.massonus.service.LectureService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/lecture_add")
public class LecturePostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final LectureService lectureService = new LectureService();
        response.setContentType("text/html");

        String subject = request.getParameter("subject");
        String description = request.getParameter("description");
        Integer teacher_id = Integer.valueOf(request.getParameter("teacher_id"));
        Integer course_id = Integer.valueOf(request.getParameter("course_id"));

        final Lecture lecture = new Lecture(subject, description, teacher_id, course_id);
        lectureService.add(lecture);

        try (PrintWriter writer = response.getWriter()) {
            writer.println("<p>Subject: " + subject + "</p>");
            writer.println("<p>Description: " + description + "</p>");
            writer.println("<p>Teacher id: " + teacher_id + "</p>");
            writer.println("<p>Course id: " + course_id + "</p>");
        }
    }
}
