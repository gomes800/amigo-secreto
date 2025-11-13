package com.gom.amigo_secreto.dto.group;

import com.gom.amigo_secreto.dto.user.UserSummaryDTO;
import com.gom.amigo_secreto.model.Group;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record GroupWithParticipantsDTO(
        Long groupId,
        String name,
        String description,
        LocalDateTime eventDate,
        BigDecimal priceLimit,
        boolean drawCompleted,
        UserSummaryDTO admin,
        List<UserSummaryDTO> participants  // Lista completa aqui
) {
    public static GroupWithParticipantsDTO fromEntity(Group group) {
        return new GroupWithParticipantsDTO(
                group.getGroupId(),
                group.getName(),
                group.getDescription(),
                group.getEventDate(),
                group.getPriceLimit(),
                group.isDrawCompleted(),
                UserSummaryDTO.fromEntity(group.getGroupAdmin()),
                group.getParticipants().stream()
                        .map(UserSummaryDTO::fromEntity)
                        .toList()
        );
    }
}
