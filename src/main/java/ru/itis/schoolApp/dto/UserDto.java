package ru.itis.schoolApp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.schoolApp.model.User;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Long avatarId;
    private String role;
    private String token;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getUserId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatarId(user.getAvatarId())
                .role(user.getRole())
                .build();
    }
}
