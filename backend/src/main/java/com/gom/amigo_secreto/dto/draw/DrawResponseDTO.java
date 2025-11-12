package com.gom.amigo_secreto.dto.draw;

import com.gom.amigo_secreto.dto.user.UserResponseDTO;
import com.gom.amigo_secreto.model.Draw;

import java.time.LocalDateTime;

public record DrawResponseDTO(
        Long drawId,
        Long groupId,
        String groupName,
        UserResponseDTO receiver,
        LocalDateTime drawDate,
        boolean revealed
) {
    public static DrawResponseDTO fromEntity(Draw draw) {
        return new DrawResponseDTO(
                draw.getDrawId(),
                draw.getGroup().getGroupId(),
                draw.getGroup().getName(),
                UserResponseDTO.fromEntity(draw.getReceiver()),
                draw.getDrawDate(),
                draw.isRevealed()
        );
    }
}
