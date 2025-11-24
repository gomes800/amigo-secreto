package com.gom.amigo_secreto.service;

import com.gom.amigo_secreto.dto.user.UserProfileUpdateDTO;
import com.gom.amigo_secreto.dto.user.UserResponseDTO;
import com.gom.amigo_secreto.exception.user.UserNotFoundException;
import com.gom.amigo_secreto.model.User;
import com.gom.amigo_secreto.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO getMyProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        return UserResponseDTO.fromEntity(user);
    }

    public Page<UserResponseDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return userRepository.findAll(pageable)
                .map(UserResponseDTO::fromEntity);
    }

    @Transactional
    public UserResponseDTO updateProfile(String email, UserProfileUpdateDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        dto.mergeInto(user);
        userRepository.save(user);

        return UserResponseDTO.fromEntity(user);
    }
}
