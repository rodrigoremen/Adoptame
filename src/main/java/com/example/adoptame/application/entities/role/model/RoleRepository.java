package com.example.adoptame.application.entities.role.model;

import com.example.adoptame.application.repository.EntityRepository;

import java.util.Optional;

public interface RoleRepository extends EntityRepository<Role,Long> {
    Optional<Role> findByAuthority(String type);
}
