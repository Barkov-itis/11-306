package ru.itis.servlets;

import com.sun.net.httpserver.HttpServer;
import ru.itis.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profile")
public class UsersServlet extends HttpServlet {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "gjhfqr102";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb_3";

    // private UsersRepository usersRepos;

    private List<User> users;

    @Override
    public void init() throws ServletException {

        users = new ArrayList<>();
        User user1 = User.builder()
                .id(1L)
                .firstName("Danil")
                .lastName("Smirnov")
                .age(22)
                .build();

        User user2 = User.builder()
                .id(2L)
                .firstName("Yzel")
                .lastName("Sysel")
                .age(15)
                .build();

        User user3 = User.builder()
                .id(3L)
                .firstName("Kirill")
                .lastName("Lapshov")
                .age(30)
                .build();

        users.add(user1);
        users.add(user2);
        users.add(user3);

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException();
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            //usersRepos = new UsersRepositoryJdbcImpl(connection);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter writer = response.getWriter();
//
//        StringBuilder resultHtml = new StringBuilder();
//
//        resultHtml.append("<!DOCTYPE html>\n" +
//        "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <title>Users</title>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<h1>Users</h1>\n" +
//                "<div>\n" +
//                "    <table>\n" +
//                "        <tr>\n" +
//                "            <th>ID</th>\n" +
//                "            <th>FIRST NAME</th>\n" +
//                "            <th>LAST NAME</th>\n" +
//                "            <th>AGE</th>\n" +
//                "        </tr>\n");
//
//        for (User user : users) {
//            resultHtml.append("<tr>\n");
//            resultHtml.append("<td>").append(user.getId()).append("</td>\n");
//            resultHtml.append("<td>").append(user.getFirstName()).append("</td>\n");
//            resultHtml.append("<td>").append(user.getLastName()).append("</td>\n");
//            resultHtml.append("<td>").append(user.getAge()).append("</td>\n");
//            resultHtml.append("</tr>");
//        }
//        resultHtml.append("    </table>\n" +
//                "</div>\n" +
//                "</body>\n" +
//                "</html>");
//
//        writer.write(resultHtml.toString());

//        request.getRequestDispatcher("html/profile.html").forward(request, response);
        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("/jsp/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first_name");
        String secondName = req.getParameter("second_name");
        System.out.println(firstName + " " + secondName);
        resp.sendRedirect("/profile");
//        User user = new User();
//        try {
            //usersRepos.save(user);_
            //usersRepos.save(firstName, seconName);_
//        } catch () {}
    }
}
