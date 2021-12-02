package ru.itis.schoolApp.servlets;

import ru.itis.schoolApp.services.LessonsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/subject")
public class SubjectLessons extends HttpServlet {
    private LessonsService lessonsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.lessonsService = (LessonsService) context.getAttribute("lessonsService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subject = request.getParameter("subject");
        request.setAttribute("lessons", lessonsService.getBySubject(subject));
        request.getRequestDispatcher("lessons.ftl").forward(request, response);
    }
}
