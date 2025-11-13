package com.gom.amigo_secreto.repository;

import com.gom.amigo_secreto.exception.draw.DrawNotFoundException;
import com.gom.amigo_secreto.model.Draw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrawRepository extends JpaRepository<Draw, Long> {

    Page<Draw> findAll(Pageable pageable);

    default Draw findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new DrawNotFoundException(id));
    }
}
