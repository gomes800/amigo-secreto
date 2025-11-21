package com.gom.amigo_secreto.dto.user;

import com.gom.amigo_secreto.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserProfileUpdateDTO(
        @NotBlank
        @Size(max = 100)
        String firstName,

        @NotBlank
        @Size(max = 100)
        String lastName,

        LocalDate birthDate,
        String gender,
        String wishList,
        String preferences
) {
    public void mergeInto(User user) {
        if (firstName != null) user.setFirstName(firstName);
        if (lastName != null) user.setLastName(lastName);
        if (birthDate != null) user.setBirthDate(birthDate);
        if (gender != null) user.setGender(gender);
        if (wishList != null) user.setWishList(wishList);
        if (preferences != null) user.setPreferences(preferences);
    }
}
