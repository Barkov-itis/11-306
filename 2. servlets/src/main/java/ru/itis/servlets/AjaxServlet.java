package ru.itis.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.dto.AjaxForm;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    private UsersService usersService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/ajax_example.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AjaxForm user = objectMapper.readValue(req.getReader(), AjaxForm.class);
        try {
            usersService.addUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<User> users;
        try {
            users = usersService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String usersAsJson = objectMapper.writeValueAsString(users);
        resp.setContentType("application/json");
        resp.getWriter().println(usersAsJson);

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersService = (UsersService) config.getServletContext().getAttribute("usersService");
    }
}
