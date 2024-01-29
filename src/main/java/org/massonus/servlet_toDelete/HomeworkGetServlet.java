package org.massonus.servlet_toDelete;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/homework")
public class HomeworkGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_oldExample.xml");
        final HomeworkRepo homeworkRepo = context.getBean("homeworkRepo", HomeworkRepo.class);
        final List<Homework> homeworkList = homeworkRepo.getHomeworkList();
*/
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Hello " + /*homeworkList +*/ "</h2>");

        writer.close();
    }
}
