package ru.itis.schoolApp.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.services.AvatarFilesService;
import ru.itis.schoolApp.services.TableOfUsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/table")
public class TableServlet extends HttpServlet {

    TableOfUsersService tableOfUsersService;

    @Override
    public void init(ServletConfig config) {
        this.tableOfUsersService =(TableOfUsersService) config.getServletContext().getAttribute("tableOfUsersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        //catch this key in ftl file
        request.setAttribute("usersTable", tableOfUsersService.getUsersSameRole(userDto));
        request.getRequestDispatcher("table.ftl").forward(request, response);
    }
}
