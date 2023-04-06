package com.example.adoptame.application.entities.color.model;

import com.example.adoptame.application.repository.EntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ColorRepository extends EntityRepository<Color,Long> {
    Optional<Color> findByName(String name);
}
