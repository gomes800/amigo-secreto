package com.gom.amigo_secreto.dto.group;

import com.gom.amigo_secreto.model.Group;
import com.gom.amigo_secreto.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GroupResponseDTO(
        Long groupId,
        String name,
        Integer size,
        LocalDateTime createDate,
        User groupAdmin,
        String description,
        LocalDateTime eventDate,
        BigDecimal priceLimit,
        boolean drawCompleted,
        String rules
) {
    public static GroupResponseDTO fromEntity(Group group) {
        return new GroupResponseDTO(
                group.getGroupId(),
                group.getName(),
                group.getSize(),
                group.getCreateDate(),
                group.getGroupAdmin(),
                group.getDescription(),
                group.getEventDate(),
                group.getPriceLimit(),
                group.isDrawCompleted(),
                group.getRules()
        );
    }
}
