package com.example.adoptame.application.entities.pet.model;

import com.example.adoptame.application.repository.EntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PetAdoptedRepository extends EntityRepository<PetAdopted,Long> {
    List<PetAdopted>findByUserId(Long idUser);
    Optional<PetAdopted> findByPetId(Long idPet);
}
