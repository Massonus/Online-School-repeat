package org.massonus.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.massonus.entity.Homework;
import org.massonus.repo.HomeworkRepo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/homework")
public class HomeworkGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        final HomeworkRepo homeworkRepo = context.getBean("homeworkRepo", HomeworkRepo.class);
        final List<Homework> homeworkList = homeworkRepo.getAllHomework();

        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Hello " + homeworkList + "</h2>");

        writer.close();
    }
}
