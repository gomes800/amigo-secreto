package com.gom.amigo_secreto.dto.group;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateGroupDTO(
        String name,
        String description,
        LocalDateTime eventDate,
        BigDecimal priceLimit,
        String rules
) {
}
