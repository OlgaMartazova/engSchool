package ru.itis.schoolApp.servlets;

import ru.itis.schoolApp.dto.LessonDto;
import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.model.Lesson;
import ru.itis.schoolApp.services.LessonsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/details")
public class DetailsServlet extends HttpServlet {
    private LessonsService lessonsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.lessonsService = (LessonsService) context.getAttribute("lessonsService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long lessonId = Long.valueOf(request.getParameter("lessonId"));
        LessonDto lessonDto = lessonsService.getLesson(lessonId);
        UserDto teacher = lessonsService.getAuthor(lessonDto);
        request.setAttribute("teacher", teacher);
        request.setAttribute("lesson", lessonDto);
        request.getRequestDispatcher("about_lesson.ftl").forward(request, response);
    }
}
