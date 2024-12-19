package ru.itis.servlets;

import ru.itis.dto.SignUpForm;
import ru.itis.services.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signUpService = (SignUpService) config.getServletContext().getAttribute("signUpService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/signUp.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpForm signUpForm = SignUpForm.builder()
                .firstName(req.getParameter("name"))
                .lastName(req.getParameter("surname"))
                .email(req.getParameter("login"))
                .password(req.getParameter("pass"))
                .age(req.getIntHeader("age"))
                .build();
        try {
            signUpService.signUp(signUpForm);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
