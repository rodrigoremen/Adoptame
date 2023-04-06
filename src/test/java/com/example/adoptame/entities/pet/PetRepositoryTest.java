package com.example.adoptame.entities.pet;

import com.example.adoptame.application.entities.pet.model.Pet;
import com.example.adoptame.application.entities.pet.model.PetRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PetRepositoryTest {
    @Autowired
    private PetRepository petRepository;

    @BeforeEach
    void setUp() {
        Pet pet1 = Pet.builder()
                .name("Luna")
                .breed("Mestiza")
                .age("2")
                .description("Es muy juguetona")
                .isActive(true)
                .isAdopted(false)
                .isDropped(false)
                .build();

        Pet pet2 = Pet.builder()
                .name("Firulais")
                .breed("Labrador")
                .age("5")
                .description("Es muy tranquilo")
                .isActive(true)
                .isAdopted(false)
                .isDropped(false)
                .build();

        Pet pet3 = Pet.builder()
                .name("Max")
                .breed("Pastor alemán")
                .age("3")
                .isActive(true)
                .isAdopted(false)
                .isDropped(false)
                .description("Es muy protector")
                .build();

        petRepository.save(pet1);
        petRepository.save(pet2);
        petRepository.save(pet3);
    }
    @Test
    void testSavePet() {
        Pet mascota = Pet.builder()
                .name("Bamby")
                .age("Año y medio")
                .gender('M')
                .description("perro bonito")
                .isAdopted(false)
                .isDropped(false)
                .isActive(true)
                .build();
        Pet mascotaSave = petRepository.save(mascota);

        Assertions.assertThat(mascotaSave).isNotNull();
        Assertions.assertThat(mascotaSave.getId()).isGreaterThan(0);
    }

    @DisplayName("Test para obtener los últimos 3 pets creados")
    @Test
    void findLastThreePets() {
        // when
        List<Pet> pets = petRepository.findLastThreePets();

        // then
        Assertions.assertThat(pets.size()).isEqualTo(3);
    }

    @DisplayName("Test para obtener todos los pets disponibles para adoptar")
    @Test
    void findPetsForAdopted() {
        // when
        List<Pet> pets = petRepository.findPetsForAdopted();

        // then
        Assertions.assertThat(pets).isNotEmpty();
    }

    @DisplayName("Test para buscar pets por nombre y raza")
    @Test
    void findAllByNameContainingOrBreedContainingAndIsActiveTrueAndIsAdoptedFalseAndIsDroppedFalse() {
        // when
        List<Pet> pets = petRepository.findAllByNameContainingOrBreedContainingAndIsActiveTrueAndIsAdoptedFalseAndIsDroppedFalse("Luna", "Pastor alemán");

        // then
        Assertions.assertThat(pets.size()).isEqualTo(2);
    }



}
