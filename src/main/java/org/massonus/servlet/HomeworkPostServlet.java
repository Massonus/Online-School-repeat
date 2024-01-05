package org.massonus.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.massonus.entity.Homework;
import org.massonus.service.HomeworkService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/homework_add")
public class HomeworkPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final HomeworkService homeworkService = new HomeworkService();
        response.setContentType("text/html");

        String task = request.getParameter("task");
        Integer lectureId = Integer.valueOf(request.getParameter("lecture_id"));

        final Homework homework = new Homework(task, lectureId);
        homeworkService.add(homework);

        try (PrintWriter writer = response.getWriter()) {
            writer.println("<p>Task: " + task + "</p>");
            writer.println("<p>Lecture id: " + lectureId + "</p>");
        }
    }
}
