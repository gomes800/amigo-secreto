package com.gom.amigo_secreto.service;

import com.gom.amigo_secreto.dto.group.CreateGroupDTO;
import com.gom.amigo_secreto.dto.group.GroupResponseDTO;
import com.gom.amigo_secreto.dto.group.UpdateGroupDTO;
import com.gom.amigo_secreto.exception.draw.DrawAlreadyCompletedException;
import com.gom.amigo_secreto.exception.group.UserAlreadyInGroupException;
import com.gom.amigo_secreto.model.Group;
import com.gom.amigo_secreto.model.User;
import com.gom.amigo_secreto.repository.GroupRepository;
import com.gom.amigo_secreto.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public Page<GroupResponseDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return groupRepository.findAll(pageable)
                .map(GroupResponseDTO::fromEntity);
    }

    public GroupResponseDTO getById(Long id) {
        Group group = groupRepository.findByIdOrThrow(id);

        return GroupResponseDTO.fromEntity(group);
    }

    @Transactional
    public GroupResponseDTO create(CreateGroupDTO dto) {
        Group group = Group.builder()
                .name(dto.name())
                .description(dto.description())
                .eventDate(dto.eventDate())
                .priceLimit(dto.priceLimit())
                .rules(dto.rules())
                .createDate(LocalDateTime.now())
                .drawCompleted(false)
                .build();

        groupRepository.save(group);

        return GroupResponseDTO.fromEntity(group);
    }

    @Transactional
    public GroupResponseDTO update(Long id, UpdateGroupDTO dto) {
        Group group = groupRepository.findByIdOrThrow(id);

        dto.mergeInto(group);
        groupRepository.save(group);

        return GroupResponseDTO.fromEntity(group);
    }

    @Transactional
    public void delete(Long id) {
        Group group = groupRepository.findByIdOrThrow(id);

        groupRepository.delete(group);
    }

    @Transactional
    public void addUser(Long groupId, Long userId) {
        Group group = groupRepository.findByIdOrThrow(groupId);
        User user = userRepository.findByIdOrThrow(userId);

        if (group.isDrawCompleted()) {
            throw new DrawAlreadyCompletedException();
        }

        boolean alreadyInGroup = group.getParticipants().stream()
                        .anyMatch(p -> p.getUserId().equals(userId));

        if (alreadyInGroup) {
            throw new UserAlreadyInGroupException(userId, groupId);
        }

        group.getParticipants().add(user);

        groupRepository.save(group);
    }
}
