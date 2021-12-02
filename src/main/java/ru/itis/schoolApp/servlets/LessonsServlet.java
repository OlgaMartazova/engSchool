package ru.itis.schoolApp.servlets;

import ru.itis.schoolApp.dto.LessonDto;
import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.services.LessonsService;
import ru.itis.schoolApp.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/lessons")
public class LessonsServlet extends HttpServlet {
    private LessonsService lessonsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.lessonsService = (LessonsService) context.getAttribute("lessonsService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        request.setAttribute("user", userDto);
        if (userDto.getRole().equals("teacher")) {
            request.setAttribute("lessons", lessonsService.getByAuthorId(userDto.getId()));
            request.getRequestDispatcher("add_lesson.ftl").forward(request, response);
        }
        else {
            request.setAttribute("lessons", lessonsService.getLessons());
            request.getRequestDispatcher("lessons.ftl").forward(request, response);
        }
    }
}
