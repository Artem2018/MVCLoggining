package controllers;
import DTOs.Resources;
import DTOs.User;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/log.servlet")
public class Controller extends HttpServletBase {
    private static final long serialVersionUID = 8947913012747959287L;

    private static final String PARAMETER_USER_NAME = "userName";
    private static final String PARAMETER_USER_PASSWORD = "userPassword";


    public void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String userName = request.getParameter(PARAMETER_USER_NAME);
        String userPassword = request.getParameter(PARAMETER_USER_PASSWORD);
        if (userName != null && !userName.isEmpty() && userPassword != null
                && !userPassword.isEmpty()) {
            try {
                User user = _repository.login(userName,userPassword);
                if (user != null){
                    Resources resources = _repository.resourceNames(user); // object of class resources
                    // pass resources to attributes
                    HttpSession session = request.getSession();
                    session.setAttribute(RESOURCES,resources);
                    ServletContext context = getServletContext();
                    RequestDispatcher dispatcher = context
                            .getRequestDispatcher("/userData.servlet");
                    dispatcher.forward(request, response);

                } else {
                    ServletContext context = getServletContext();
                    RequestDispatcher dispatcher = context
                            .getRequestDispatcher("/index.html");
                    dispatcher.forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
