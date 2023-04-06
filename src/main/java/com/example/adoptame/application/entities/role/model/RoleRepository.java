package com.example.adoptame.application.entities.role.model;

import com.example.adoptame.application.repository.EntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends EntityRepository<Role,Long> {
    Optional<Role> findByAuthority(String type);
}
