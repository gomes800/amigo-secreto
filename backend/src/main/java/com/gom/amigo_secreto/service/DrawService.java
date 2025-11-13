package com.gom.amigo_secreto.service;

import com.gom.amigo_secreto.dto.draw.DrawResponseDTO;
import com.gom.amigo_secreto.exception.draw.DrawAlreadyCompletedException;
import com.gom.amigo_secreto.exception.draw.InsufficientParticipantsException;
import com.gom.amigo_secreto.model.Draw;
import com.gom.amigo_secreto.model.Group;
import com.gom.amigo_secreto.model.User;
import com.gom.amigo_secreto.repository.DrawRepository;
import com.gom.amigo_secreto.repository.GroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DrawService {

    private final DrawRepository drawRepository;
    private final GroupRepository groupRepository;
    public DrawService(DrawRepository drawRepository, GroupRepository groupRepository) {
        this.drawRepository = drawRepository;
        this.groupRepository = groupRepository;
    }

    public Page<DrawResponseDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return drawRepository.findAll(pageable)
                .map(DrawResponseDTO::fromEntity);
    }

    public DrawResponseDTO getById(Long id) {
        Draw draw = drawRepository.findByIdOrThrow(id);

        return DrawResponseDTO.fromEntity(draw);
    }


    @Transactional
    public List<DrawResponseDTO> createDraw(Long groupId) {
        Group group = groupRepository.findByIdOrThrow(groupId);

        if (group.isDrawCompleted()) {
            throw new DrawAlreadyCompletedException();
        }

        List<User> participants = group.getParticipants();

        if (participants.size() < 3) {
            throw new InsufficientParticipantsException();
        }

        List<Draw> draws = createDrawsCycle(group, participants);

        drawRepository.saveAll(draws);

        group.setDrawCompleted(true);
        groupRepository.save(group);

        return draws.stream()
                .map(DrawResponseDTO::fromEntity)
                .toList();

    }

    private List<Draw> createDrawsCycle(Group group, List<User> participants) {
        List<User> shuffled = new ArrayList<>(participants);
        Collections.shuffle(shuffled);

        List<Draw> draws = new ArrayList<>();

        for (int i = 0; i < shuffled.size(); i++) {
            User giver = shuffled.get(i);
            User receiver = shuffled.get((i + 1) % shuffled.size());

            Draw draw = Draw.builder()
                    .group(group)
                    .giver(giver)
                    .receiver(receiver)
                    .drawDate(LocalDateTime.now())
                    .revealed(false)
                    .build();

            draws.add(draw);
        }

        return draws;
    }
}
