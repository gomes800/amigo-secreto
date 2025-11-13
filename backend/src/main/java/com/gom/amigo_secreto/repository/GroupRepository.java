package com.gom.amigo_secreto.repository;

import com.gom.amigo_secreto.exception.group.GroupNotFoundException;
import com.gom.amigo_secreto.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Page<Group> findAll(Pageable pageable);

    default Group findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
    }
}
