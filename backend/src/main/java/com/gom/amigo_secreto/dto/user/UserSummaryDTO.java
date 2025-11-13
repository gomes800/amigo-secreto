package com.gom.amigo_secreto.dto.user;

import com.gom.amigo_secreto.model.User;

public record UserSummaryDTO(
        Long userId,
        String firstName,
        String lastName,
        String wishList
) {
    public static UserSummaryDTO fromEntity(User user) {
        return new UserSummaryDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getWishList()
        );
    }
}
