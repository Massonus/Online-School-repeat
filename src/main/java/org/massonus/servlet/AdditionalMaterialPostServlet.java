package org.massonus.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.massonus.entity.AdditionalMaterial;
import org.massonus.entity.ResourceType;
import org.massonus.service.AdditionalMaterialsService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/material_add")
public class AdditionalMaterialPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        final AdditionalMaterialsService materialsService = context.getBean("materialsService", AdditionalMaterialsService.class);
        response.setContentType("text/html");

        String task = request.getParameter("task");
        ResourceType resourceType = ResourceType.valueOf(request.getParameter("resource_type"));
        Integer lectureId = Integer.valueOf(request.getParameter("lecture_id"));

        final AdditionalMaterial material = new AdditionalMaterial(task, resourceType, lectureId);
        materialsService.add(material);

        try (PrintWriter writer = response.getWriter()) {
            writer.println("<p>Task: " + task + "</p>");
            writer.println("<p>Resource Type: " + resourceType + "</p>");
            writer.println("<p>Lecture id: " + lectureId + "</p>");
        }
    }
}
