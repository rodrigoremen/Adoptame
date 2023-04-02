package com.example.adoptame.application.entities.character.model;

import com.example.adoptame.application.repository.EntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CharacterRepository extends EntityRepository<Character,Long> {
    List<Character>findAllByStatus(Boolean status);
    Optional<Character> findByName(String name);
}
