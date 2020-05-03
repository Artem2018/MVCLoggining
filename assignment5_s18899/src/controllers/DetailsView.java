package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/details.servlet")
public class DetailsView extends HttpServletBase {
    private static final long serialVersionUID = -451882692362946093L;

    private static final String CONTENT = "<!DOCTYPE html>" //
            + "<html>" //
            + "<body>" //
            + "Details:<br>" //
            + "%s<br>" //
            + "<br><form method=\"post\" action=\"/index.html\"><button type=\"submit\">logout</button></form><br>"//
            + "<form method=\"post\" action=\"/userData.servlet\"><button type=\"submit\">back to resources</button></form>"//
            + "</body>" //
            + "</html>";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String details =  req.getQueryString();
        out.printf(CONTENT,details);
        out.close();
    }
}
