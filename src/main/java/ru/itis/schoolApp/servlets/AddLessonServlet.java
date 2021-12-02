package ru.itis.schoolApp.servlets;

import ru.itis.schoolApp.dto.LessonDto;
import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.services.LessonsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/add-lesson")
public class AddLessonServlet extends HttpServlet {
    private LessonsService lessonsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.lessonsService = (LessonsService) context.getAttribute("lessonsService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        LessonDto form = LessonDto.builder()
                .content(request.getParameter("content"))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .author(userDto)
                .subject(request.getParameter("subject"))
                .forWhom(request.getParameter("for_whom"))
                .build();
        LessonDto createdLesson = lessonsService.addLesson(form);
        request.setAttribute("createdLesson", createdLesson);
        response.sendRedirect("/lessons");
    }
}
