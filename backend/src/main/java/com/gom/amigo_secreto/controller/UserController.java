package com.gom.amigo_secreto.controller;

import com.gom.amigo_secreto.dto.user.UserProfileUpdateDTO;
import com.gom.amigo_secreto.dto.user.UserResponseDTO;
import com.gom.amigo_secreto.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(userService.getAll(page, size));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyProfile(@AuthenticationPrincipal OAuth2User oAuth2User) {
        return ResponseEntity.ok(userService.getMyProfile(oAuth2User.getAttribute("email")));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserResponseDTO> updateProfile(
            @AuthenticationPrincipal OAuth2User oAuth2User,
            @Valid @RequestBody UserProfileUpdateDTO dto) {
        return ResponseEntity.ok(userService.updateProfile(oAuth2User.getAttribute("email"), dto));
    }

}
