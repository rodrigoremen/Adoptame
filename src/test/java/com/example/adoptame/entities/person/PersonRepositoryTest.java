package com.example.adoptame.entities.person;

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

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    private Address address;
    private Address address2;
    private Address address3;
    private Address address4;
    private Person person;
    private Person person2;
    private Person person3;
    private Person person4;

    private User user;
    private User user2;
    private User user3;
    private User user4;

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

         user2 = User.builder()
                .username("mariana.rodriguez@gmail.com")
                .password("securepassword")
                .isActive(true)
                .build();

         person2 = Person.builder()
                .firstName("Mariana")
                .lastName("Rodriguez")
                .surName("Lopez")
                .telephone("555-987-6543")
                .imageProfileUrl("https://www.example.com/profile2.jpg")
                .build();

         address2 = Address.builder()
                .street("Calle del Sol")
                .internalNumber("10")
                .externalNumber("0")
                .zipCode("12345")
                .suburb("Centro")
                .town("Mexico City")
                .state("Ciudad de Mexico")
                .build();

         user3 = User.builder()
                .username("juan.perez@hotmail.com")
                .password("password123")
                .isActive(false)
                .build();

         person3 = Person.builder()
                .firstName("Juan")
                .lastName("Perez")
                .surName("Gomez")
                .telephone("444-123-4567")
                .imageProfileUrl("https://www.example.com/profile3.jpg")
                .build();

         address3 = Address.builder()
                .street("Calle de la Luna")
                .internalNumber("20")
                .externalNumber("0")
                .zipCode("54321")
                .suburb("San Miguel")
                .town("Guadalajara")
                .state("Jalisco")
                .build();

         user4 = User.builder()
                .username("pedro.martinez@gmail.com")
                .password("securepassword123")
                .isActive(true)
                .build();

         person4 = Person.builder()
                .firstName("Pedro")
                .lastName("Martinez")
                .surName("Lopez")
                .telephone("777-123-4567")
                .imageProfileUrl("https://www.example.com/profile4.jpg")
                .build();

         address4 = Address.builder()
                .street("Calle de la Montaña")
                .internalNumber("30")
                .externalNumber("0")
                .zipCode("98765")
                .suburb("La Montaña")
                .town("Monterrey")
                .state("Nuevo Leon")
                .build();


    }

    @DisplayName("Test para realizar el registro de una persona con direccion y usuario")
    @Test
    void savePerson() {
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

    @DisplayName("Test para actualizar los datos de una persona")
    @Test
    void updatePerson() {

       User userTest = userRepository.save(user);
       person.setUser(userTest);
      Person personTest = personRepository.save(person);
      address.setPerson(personTest);
      Address addressTest = addressRepository.save(address);
        // actualiza la persona
        Person updatedPerson = personTest.toBuilder()
                .firstName("Nuevo nombre")
                .lastName("Nuevo apellido")
                .telephone("777-510-00-00")
                .build();

        personRepository.save(updatedPerson);

        // obtiene la persona actualizada y la dirección asociada
        Optional<Person> optionalPerson = personRepository.findByUser(userTest);
        Optional<Address> optionalAddress = addressRepository.findById(addressTest.getId());

        // verifica que la persona y la dirección hayan sido actualizadas correctamente
        Assertions.assertThat(optionalPerson).isPresent();
        Assertions.assertThat(optionalAddress).isPresent();
        Assertions.assertThat(optionalPerson.get().getFirstName()).isEqualTo("Nuevo nombre");
        Assertions.assertThat(optionalPerson.get().getLastName()).isEqualTo("Nuevo apellido");
        Assertions.assertThat(optionalPerson.get().getTelephone()).isEqualTo("777-510-00-00");
        Assertions.assertThat(optionalAddress.get().getStreet()).isEqualTo("Avenida revoucion");
        Assertions.assertThat(optionalAddress.get().getInternalNumber()).isEqualTo("57");
        Assertions.assertThat(optionalAddress.get().getExternalNumber()).isEqualTo("0");
        Assertions.assertThat(optionalAddress.get().getZipCode()).isEqualTo("62564");
        Assertions.assertThat(optionalAddress.get().getSuburb()).isEqualTo("el castillo");
        Assertions.assertThat(optionalAddress.get().getTown()).isEqualTo("Jiutepec");
        Assertions.assertThat(optionalAddress.get().getState()).isEqualTo("Morelos");
    }

    @DisplayName("Test para obtener a todas las personas")
    @Test
    void findAllPersons() {
        // usuario 1
       User userTest1 = userRepository.save(user);
       person.setUser(userTest1);
       Person personTest1 = personRepository.save(person);
       address.setPerson(personTest1);
       Address addressTest1 = addressRepository.save(address);

        // usuario 2

        User userTest2 = userRepository.save(user2);
        person2.setUser(userTest1);
        Person personTest2 = personRepository.save(person2);
        address2.setPerson(personTest2);
        Address addressTest2 = addressRepository.save(address2);

        // usario 3

        User userTest3 = userRepository.save(user3);
        person3.setUser(userTest1);
        Person personTest3 = personRepository.save(person3);
        address3.setPerson(personTest3);
        Address addressTest3 = addressRepository.save(address3);

        // when
        List<Person> personList = personRepository.findAll();

        //then
        Assertions.assertThat(personList).isNotNull();
        Assertions.assertThat(personList.size()).isEqualTo(3);


    }

    @DisplayName("Test para obtener los datos de una persona por usuario")
    @Test
    void findPersonByUser() {

        User user5 = userRepository.save(user);
        person.setUser(user5);
        Person person5 = personRepository.save(person);


        Optional<Person> result = personRepository.findByUser(user5);

        // then
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getFirstName()).isEqualTo(person5.getFirstName());
        Assertions.assertThat(result.get().getLastName()).isEqualTo(person5.getLastName());
        Assertions.assertThat(result.get().getSurName()).isEqualTo(person5.getSurName());
        Assertions.assertThat(result.get().getTelephone()).isEqualTo(person5.getTelephone());
        Assertions.assertThat(result.get().getImageProfileUrl()).isEqualTo(person5.getImageProfileUrl());
    }

}
