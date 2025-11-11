package com.gom.amigo_secreto.dto.group;

import com.gom.amigo_secreto.model.Group;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateGroupDTO(
        String name,
        String description,
        LocalDateTime eventDate,
        BigDecimal priceLimit,
        String rules
) {
    public void mergeInto(Group group) {
        if (name != null) group.setName(name);
        if (description != null) group.setDescription(description);
        if (eventDate != null) group.setEventDate(eventDate);
        if (priceLimit != null) group.setPriceLimit(priceLimit);
        if (rules != null) group.setRules(rules);
    }
}
