package ru.itis.schoolApp.servlets;

import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.dto.UserForm;
import ru.itis.schoolApp.exception.ValidationException;
import ru.itis.schoolApp.services.EditProfileService;
import ru.itis.schoolApp.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/edit")
public class EditProfileServlet extends HttpServlet {
    private EditProfileService editProfileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.editProfileService = (EditProfileService) context.getAttribute("editProfileService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("edit_profile.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserForm form = UserForm.builder()
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .role(request.getParameter("role"))
                .build();


        HttpSession session = request.getSession(true);
        UserDto userDto = (UserDto) session.getAttribute("user");
        try {
            userDto = editProfileService.edit(form, userDto);
        } catch (Exception e) {
            response.sendRedirect("/profile");
            return;
        }
        session.setAttribute("user", userDto);
        response.sendRedirect("/profile");
    }
}
