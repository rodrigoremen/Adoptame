package com.example.adoptame.entities.pet;

import com.example.adoptame.application.entities.pet.model.PetAdoptedRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PetAdoptedRepositoryTest {
    @Autowired
    private PetAdoptedRepository petAdoptedRepository;

    @Test
    void testAdoptedRegister(){

    }

}
