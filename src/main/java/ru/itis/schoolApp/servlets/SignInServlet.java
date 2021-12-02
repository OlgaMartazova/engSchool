package ru.itis.schoolApp.servlets;

import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.dto.UserForm;
import ru.itis.schoolApp.exception.ValidationException;
import ru.itis.schoolApp.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.signInService = (SignInService) context.getAttribute("signInService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("sign_in.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserForm form = UserForm.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();
        UserDto userDto;

        try {
            userDto = signInService.signIn(form);
        } catch (ValidationException e) {
            response.sendRedirect("/sign-in");
            return;
        }
        Cookie cookie = new Cookie("token", userDto.getToken());
        response.addCookie(cookie);
        HttpSession session = request.getSession(true);
        session.setAttribute("user", userDto);
        response.sendRedirect("/profile");
    }
}
