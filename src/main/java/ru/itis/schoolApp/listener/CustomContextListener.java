package ru.itis.schoolApp.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.schoolApp.dao.AvatarFilesRepository;
import ru.itis.schoolApp.dao.LessonsRepository;
import ru.itis.schoolApp.dao.UsersRepository;
import ru.itis.schoolApp.dao.impl.AvatarFilesRepositoryImpl;
import ru.itis.schoolApp.dao.impl.LessonsRepositoryImpl;
import ru.itis.schoolApp.dao.impl.UsersRepositoryImpl;
import ru.itis.schoolApp.services.*;
import ru.itis.schoolApp.services.impl.*;
import ru.itis.schoolApp.services.validation.Validator;
import ru.itis.schoolApp.services.validation.impl.ValidatorImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebListener
public class CustomContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String DB_USERNAME;
        String DB_PASSWORD;
        String DB_URL;
        String DB_DRIVER;
        String JWT_SECRET;
        String IMAGES_STORAGE_PATH;
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Требуется файл properties");
        }
        DB_USERNAME = (String) properties.get("spring.datasource.username");
        DB_PASSWORD = (String) properties.get("spring.datasource.password");
        DB_URL = (String) properties.get("spring.datasource.url");
        DB_DRIVER = (String) properties.get("spring.datasource.driver-class-name");
        IMAGES_STORAGE_PATH = (String) properties.get("storage.images");

        ServletContext servletContext = servletContextEvent.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
        LessonsRepository lessonsRepository = new LessonsRepositoryImpl(dataSource);
        AvatarFilesRepository avatarFilesRepository = new AvatarFilesRepositoryImpl(dataSource);
        ObjectMapper objectMapper = new ObjectMapper();

        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        Validator validator = new ValidatorImpl(usersRepository);

        SignUpService signUpService = new SignUpServiceImpl(usersRepository, passwordEncoder, validator);
        SignInService signInService = new SignInServiceImpl(usersRepository, passwordEncoder);
        EditProfileService editProfileService = new EditProfileServiceImpl(usersRepository);
        AvatarFilesService avatarFilesService = new AvatarFilesServiceImpl(IMAGES_STORAGE_PATH, avatarFilesRepository, usersRepository);
        TableOfUsersService tableOfUsersService = new TableOfUsersServiceImpl(usersRepository);
        LessonsService lessonsService = new LessonsServiceImpl(lessonsRepository, usersRepository);

        servletContext.setAttribute("objectMapper", objectMapper);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("editProfileService", editProfileService);
        servletContext.setAttribute("passwordEncoder", passwordEncoder);
        servletContext.setAttribute("avatarFilesService", avatarFilesService);
        servletContext.setAttribute("tableOfUsersService", tableOfUsersService);
        servletContext.setAttribute("lessonsService", lessonsService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
