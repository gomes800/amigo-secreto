package com.gom.amigo_secreto.service;

import com.gom.amigo_secreto.dto.user.UserResponseDTO;
import com.gom.amigo_secreto.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserResponseDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return userRepository.findAll(pageable)
                .map(UserResponseDTO::fromEntity);
    }
}
