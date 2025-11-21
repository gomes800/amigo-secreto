package com.gom.amigo_secreto.controller;

import com.gom.amigo_secreto.dto.group.CreateGroupDTO;
import com.gom.amigo_secreto.dto.group.GroupResponseDTO;
import com.gom.amigo_secreto.dto.group.GroupWithParticipantsDTO;
import com.gom.amigo_secreto.dto.group.UpdateGroupDTO;
import com.gom.amigo_secreto.service.GroupService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<Page<GroupResponseDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(groupService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getById(id));
    }

    @PostMapping
    public ResponseEntity<GroupResponseDTO> create(
            @AuthenticationPrincipal OAuth2User oAuth2User,
            @RequestBody CreateGroupDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.create(oAuth2User.getAttribute("email"),dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GroupResponseDTO> update(@PathVariable Long id, @RequestBody UpdateGroupDTO dto) {
        return ResponseEntity.ok(groupService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        groupService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<GroupWithParticipantsDTO> getWithParticipants(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getWithParticipants(id));
    }

    @PostMapping("/{groupId}/add-user/{userId}")
    public ResponseEntity<Void> addUser(
            @PathVariable Long groupId,
            @PathVariable Long userId) {

        groupService.addUser(groupId, userId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
