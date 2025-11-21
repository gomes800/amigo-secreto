package com.gom.amigo_secreto.repository;

import com.gom.amigo_secreto.exception.user.UserNotFoundException;
import com.gom.amigo_secreto.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    default User findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    Optional<User> findByEmail(String email);
    Optional<User> findByProviderAndProviderId(String provider, String providerId);
    Optional<User> findByUsername(String username);
}
