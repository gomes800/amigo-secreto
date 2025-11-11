package com.gom.amigo_secreto.dto.user;

import com.gom.amigo_secreto.model.User;

import java.time.LocalDate;


public record UserResponseDTO(
        Long userId,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String gender,
        String wishList,
        String preferences
) {
    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getGender(),
                user.getWishList(),
                user.getPreferences()
        );
    }
}
