package com.example.adoptame.application.entities.type.model;

import com.example.adoptame.application.entities.type.model.Type;
import com.example.adoptame.application.repository.EntityRepository;

import java.util.List;
import java.util.Optional;

public interface TypeRepository extends EntityRepository<Type,Long> {
    List<Type> findAllByStatus(Boolean status);
    Optional<Type> findByName(String name);
}
