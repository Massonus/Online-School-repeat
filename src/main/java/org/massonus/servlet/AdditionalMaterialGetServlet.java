package org.massonus.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.massonus.entity.AdditionalMaterial;
import org.massonus.repo.AdditionalMaterialsRepo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/materials")
public class AdditionalMaterialGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        final AdditionalMaterialsRepo additionalMaterialsRepo = new AdditionalMaterialsRepo();
        final List<AdditionalMaterial> materials = additionalMaterialsRepo.getAllMaterials();

        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Hello " + materials + "</h2>");

        writer.close();
    }
}
