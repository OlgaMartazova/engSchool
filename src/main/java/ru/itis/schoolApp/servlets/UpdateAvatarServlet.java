package ru.itis.schoolApp.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.schoolApp.dto.UserDto;
import ru.itis.schoolApp.model.AvatarFile;
import ru.itis.schoolApp.services.AvatarFilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/update-avatar")
@MultipartConfig
public class UpdateAvatarServlet extends HttpServlet {
    private AvatarFilesService avatarFilesService;
    private ObjectMapper objectMapper;

    @Override
    public void init(ServletConfig config) {
        objectMapper = (ObjectMapper) config.getServletContext().getAttribute("objectMapper");
        this.avatarFilesService =(AvatarFilesService)config.getServletContext().getAttribute("avatarFilesService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String line;
//        while ((line = request.getReader().readLine()) != null) {
//            System.out.println(line);
//        }
        Part part = request.getPart("file");
        HttpSession session = request.getSession(true);
        UserDto userDto = (UserDto) session.getAttribute("user");
        AvatarFile avatar = avatarFilesService.uploadAvatar(
                userDto,
                part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                part.getSize());
        userDto.setAvatarId(avatar.getFileId());
        session.setAttribute("user", userDto);
        objectMapper.writeValue(response.getOutputStream(), avatar);
        response.setContentType("application/json");
    }
}
