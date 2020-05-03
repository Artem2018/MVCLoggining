import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/error")
public final class ErrorServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6596622673660967515L;
	
	private static final String ExceptionTypeAttribute = "javax.servlet.error.exception_type";
	private static final String ExceptionAttribute = "javax.servlet.error.exception";

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Class<?> exceptionType = (Class<?>)req.getAttribute(ExceptionTypeAttribute);
		WebException exc = (WebException) req.getAttribute(ExceptionAttribute);
		if (exc != null) {
			PrintWriter out = resp.getWriter();
			out.println("<html><body>");
			out.print("<h2>" + exceptionType.getCanonicalName() + "</h2><br>");
			out.println("<h2>" + exc.getMessage() + "</h2><hr>");
			Throwable cause = exc.getCause();
			if (cause instanceof SQLException) {
				out.print("<h2>" + cause.getClass().getCanonicalName() + "</h2><br>");
				SQLException sqlexc = (SQLException) cause;
				out.println(sqlexc.getMessage() + "<br><br>");
				out.println("Error code: " + sqlexc.getErrorCode() + "<br>");
				out.println("SQL state : " + sqlexc.getSQLState() + "<br>");
			}
			out.print("</body></html>");
			out.close();
		}
	}
}