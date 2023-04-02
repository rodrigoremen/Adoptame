package com.example.adoptame.application.entities.pet.model;

import com.example.adoptame.application.entities.color.model.Color;
import com.example.adoptame.application.entities.type.model.Type;
import com.example.adoptame.application.repository.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
@Repository
public interface PetRepository extends EntityRepository<Pet,Long> {
    @Query(value = "SELECT * FROM tbl_pets p WHERE p.is_active = 1 AND p.is_adopted = 0 AND p.is_dropped = 0 ORDER BY created_at DESC LIMIT 3;",
            nativeQuery = true)
    List<Pet> findLastThreePets();

    @Query(value = "SELECT * FROM tbl_pets p WHERE p.is_active = 1 AND p.is_adopted = 0 AND p.is_dropped = 0 ORDER BY created_at DESC;",
            nativeQuery = true)
    List<Pet> findPetsForAdopted();
    List<Pet>findAllByIsActiveAndIsDroppedFalseOrderByCreatedAtDesc(Boolean isActive);
    List<Pet> findTop5ByCreatedAtDesc();

    Integer countByIsActiveAndIsDroppedFalse(Boolean isActive);

    Integer countByIsAdoptedAndIsDroppedFalse(Boolean isAdopted);
    List<Pet> findAllByNameContainingOrBreedContainingAndIsActiveTrueAndIsAdoptedFalseAndIsDroppedFalse(String name, String breed);

    List<Pet> findByTypeInAndIsActiveTrueAndIsAdoptedFalseAndIsDroppedFalse(Collection<Type> types);
    List<Pet> findByCharacterInAndIsActiveTrueAndIsAdoptedFalseAndIsDroppedFalse(Collection<Character> characters);
    List<Pet> findByColorInAndIsActiveTrueAndIsAdoptedFalseAndIsDroppedFalse(Collection<Color> colors);

    List<Pet> findByAgeInAndIsActiveTrueAndIsAdoptedFalseAndIsDroppedFalse(Collection<String> age);
}
