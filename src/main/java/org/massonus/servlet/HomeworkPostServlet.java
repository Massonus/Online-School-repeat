package org.massonus.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.massonus.entity.Homework;
import org.massonus.service.HomeworkService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/homework_add")
public class HomeworkPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_oldExample.xml");
        final HomeworkService homeworkService = context.getBean("homeworkService", HomeworkService.class);

        response.setContentType("text/html");

        String task = request.getParameter("task");
        Integer lectureId = Integer.valueOf(request.getParameter("lecture_id"));

        final Homework homework = new Homework();
        homework.setTask(task);
        homeworkService.add(homework);

        try (PrintWriter writer = response.getWriter()) {
            writer.println("<p>Task: " + task + "</p>");
            writer.println("<p>Lecture id: " + lectureId + "</p>");
        }
    }
}
