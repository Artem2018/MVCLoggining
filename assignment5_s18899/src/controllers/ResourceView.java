package controllers;

import DTOs.ResourceDetails;
import DTOs.Resources;
import DTOs.UserResource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/userData.servlet")
public class ResourceView extends HttpServletBase {

	private static final long serialVersionUID = -1447481537208390095L;

	private static final String CONTENT = "<!DOCTYPE html>" //
			+ "<html>" //
			+ "<body>" //
			+ "<form method=\"post\" action=\"/index.html\"><button type=\"submit\">logout</button></form>"//
			+ "<br>ALL RESOURCES:<br>" //
			+ "</body>" //
			+ "</html>";

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        HttpSession session = req.getSession();
		Resources attributeResources = (Resources) session.getAttribute(RESOURCES);
		PrintWriter writer = resp.getWriter();
		writer.print(CONTENT);
		List<UserResource> list = attributeResources.get_res();
		for (UserResource r : list) {
			ResourceDetails details;
			int id = r.get_id();
			try {
				details = _repository.resourceDetail(id);
                writer.println(r + "<br>\t<form method=\"post\" action=\"/details.servlet?" + details + "\">" +
                        "<br><input type=\"submit\" value=\"details\"></button></form><br>");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		writer.close();
	}
}