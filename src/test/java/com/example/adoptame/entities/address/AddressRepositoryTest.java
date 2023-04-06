package com.example.adoptame.entities.address;

import com.example.adoptame.application.entities.address.model.Address;
import com.example.adoptame.application.entities.address.model.AddressRepository;
import com.example.adoptame.application.entities.person.model.Person;
import com.example.adoptame.application.entities.person.model.PersonRepository;
import com.example.adoptame.application.entities.user.model.User;
import com.example.adoptame.application.entities.user.model.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    private Address address;
    private Person person;
    private User user;

    @BeforeEach
    void setUp() {

        user = User.builder()
                .username("alexuandermacha@gmail.com")
                .password("12345cris")
                .isActive(true)
                .build();

        person = Person.builder()
                .firstName("Alexander")
                .lastName("Machado")
                .surName("Ambrocio")
                .telephone("777-510-29-28")
                .imageProfileUrl("https://www.ejemplo.com/?parametro=valor")
                .build();
        address = Address.builder()
                .street("Avenida revoucion")
                .internalNumber("57")
                .externalNumber("0")
                .zipCode("62564")
                .suburb("el castillo")
                .town("Jiutepec")
                .state("Morelos").build();
    }

    @DisplayName("Test para realizar el registro de una persona con direccion y usuario")
    @Test
    void saveAdress() {
        // guarda el usuario
        User user1 = userRepository.save(user);
        person.setUser(user1);
        //guarda la persona
        Person person1 = personRepository.save(person);
        address.setPerson(person1);
        // guarda la direccion
        Address address1 = addressRepository.save(address);

        // evaaluar las inserciones y evaluaciones
        Assertions.assertThat(address1).isNotNull();
        Assertions.assertThat(address1.getPerson()).isEqualTo(person1);
        Assertions.assertThat(person1.getUser()).isEqualTo(user1);
    }

    @DisplayName("Test para actualizar una direccion")
    @Test
    void updateAdress() {

    }

    @DisplayName("Test para eliminar una direccion")
    @Test
    void deleteAdress() {

    }
}
