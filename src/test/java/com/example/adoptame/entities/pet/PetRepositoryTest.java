package com.example.adoptame.entities.pet;

import com.example.adoptame.application.entities.pet.model.Pet;
import com.example.adoptame.application.entities.pet.model.PetRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PetRepositoryTest {
    @Autowired
    private PetRepository petRepository;

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


}
