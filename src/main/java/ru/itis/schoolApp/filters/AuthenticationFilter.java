package ru.itis.schoolApp.filters;

import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.exception.ValidationException;
import ru.itis.schoolApp.services.SignInService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private SignInService signInService;

    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext context = filterConfig.getServletContext();
        this.signInService = (SignInService) context.getAttribute("signInService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(request.getRequestURI().startsWith("/resources")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        boolean isAuthenticated = false;

        boolean isRequestOnAuthPage = request.getRequestURI().equals("/sign-in") ||
                request.getRequestURI().equals("/sign-up");

        if (session != null) {
            isAuthenticated = session.getAttribute("user") != null;
        }

        if (!isAuthenticated) {
            Optional<Cookie> optionalTokenCookie = Arrays.stream(request.getCookies())
                    .filter(item -> item.getName().equals("token"))
                    .findFirst();
            if (optionalTokenCookie.isPresent()) {
                String token = optionalTokenCookie.get().getValue();
                try {
                    UserDto userDto = signInService.signIn(token);
                    session = request.getSession(true);
                    session.setAttribute("user", userDto);
                    isAuthenticated = true;
                } catch (ValidationException ignored) {}
            }
        }
        //isAuthenticated && !isRequestOnAuthPage || !isAuthenticated && isRequestOnAuthPage = isAuthenticated ^ isRequestOnAuthPage
        if (isAuthenticated ^ isRequestOnAuthPage) {
            filterChain.doFilter(request, response);
        } else if (isAuthenticated) {
            response.sendRedirect("/profile");
        }  else {
            response.sendRedirect("/sign-in");
        }
    }

    @Override
    public void destroy() {}
}
