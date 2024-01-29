package org.massonus.servlet_toDelete;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/materials")
public class AdditionalMaterialGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*final AdditionalMaterialRepo materialRepo = new AdditionalMaterialRepo();
        final List<AdditionalMaterial> materials = materialRepo.getMaterialList();
*/
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        writer.println("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\"\n" +
                "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\"/>\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2 class=\"materials\">" + /*materials +*/ "</h2>\n" +
                "</body>\n" +
                "</html>");

        writer.close();
    }
}
