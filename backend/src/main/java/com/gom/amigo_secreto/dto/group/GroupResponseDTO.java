package com.gom.amigo_secreto.dto.group;

import com.gom.amigo_secreto.dto.user.UserSummaryDTO;
import com.gom.amigo_secreto.model.Group;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GroupResponseDTO(
        Long groupId,
        String name,
        Integer size,
        LocalDateTime createDate,
        UserSummaryDTO groupAdmin,
        String description,
        LocalDateTime eventDate,
        BigDecimal priceLimit,
        boolean drawCompleted,
        String rules,
        int participants
) {
    public static GroupResponseDTO fromEntity(Group group) {
        return new GroupResponseDTO(
                group.getGroupId(),
                group.getName(),
                group.getSize(),
                group.getCreateDate(),
                group.getGroupAdmin() != null ? UserSummaryDTO.fromEntity(group.getGroupAdmin()) : null,
                group.getDescription(),
                group.getEventDate(),
                group.getPriceLimit(),
                group.isDrawCompleted(),
                group.getRules(),
                group.getParticipants() != null ? group.getParticipants().size() : 0
        );
    }
}
