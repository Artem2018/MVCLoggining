package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/date")
public class DateServlet extends HttpServlet {

	private static final long serialVersionUID = 1899925489862548124L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request, response);
	}

	private void serviceRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			Date now = new Date();
			out.print("{ \"currentTime\" : \"" + now + "\" }");
		} catch (Throwable ex) {
			System.out.println(ex);
		}
	}
}