package com.gom.amigo_secreto.model.dto;

import com.gom.amigo_secreto.model.Group;
import com.gom.amigo_secreto.model.User;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String wishList;
    private String preferences;

    public static UserResponseDTO fromEntity(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .wishList(user.getWishList())
                .preferences(user.getPreferences())
                .build();
    }
}
