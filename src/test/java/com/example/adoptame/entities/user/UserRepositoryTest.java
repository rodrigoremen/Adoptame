package com.example.adoptame.entities.user;

import com.example.adoptame.application.entities.address.model.AddressRepository;
import com.example.adoptame.application.entities.person.model.PersonRepository;
import com.example.adoptame.application.entities.role.model.Role;
import com.example.adoptame.application.entities.role.model.RoleRepository;
import com.example.adoptame.application.entities.user.model.User;
import com.example.adoptame.application.entities.user.model.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private User user;
    private User user2;
    private User user3;
    private Role adoptador;
    private Role voluntario;

    @BeforeEach
    void setUp() {

        adoptador = Role.builder()
                .authority("ROL_ADOPTER")
                .build();
        voluntario = Role.builder()
                .authority("ROL_VOLUNTEER")
                .build();

         user = User.builder()
                .username("user1@example.com")
                .password("password1")
                .isActive(true)
                .build();

         user2 = User.builder()
                .username("user2@example.com")
                .password("password2")
                .isActive(true)
                .build();

        user3 = User.builder()
                .username("user3@example.com")
                .password("password3")
                .isActive(false)
                .build();
    }
    @DisplayName("Test para realizar el registro de una persona con direccion y usuario")
    @Test
    void savePerson() {
        // guarda el usuario
        User user1 = userRepository.save(user);
    }


    @DisplayName("Test para buscar un usuario activo por nombre de usuario")
    @Test
    void findByUsernameAndIsActive_whenUserExistsAndIsActive_shouldReturnUser() {
        Optional<User> optionalUser = userRepository.findByUsernameAndIsActive("user1@example.com", true);
        Assertions.assertThat(optionalUser).isPresent();
        Assertions.assertThat(optionalUser.get().getUsername()).isEqualTo("user1@example.com");
        Assertions.assertThat(optionalUser.get().getPassword()).isEqualTo("password1");
        Assertions.assertThat(optionalUser.get().getIsActive()).isTrue();
    }

    @DisplayName("Test para buscar un usuario inactivo por nombre de usuario")
    @Test
    void findByUsernameAndIsActive_whenUserExistsAndIsNotActive_shouldNotReturnUser() {
        Optional<User> optionalUser = userRepository.findByUsernameAndIsActive("user3@example.com", true);
        Assertions.assertThat(optionalUser).isNotPresent();
    }

    @DisplayName("Test para buscar un usuario por nombre de usuario")
    @Test
    void findByUsername_whenUserExists_shouldReturnUser() {
        Optional<User> optionalUser = userRepository.findByUsername("user1@example.com");
        Assertions.assertThat(optionalUser).isPresent();
        Assertions.assertThat(optionalUser.get().getUsername()).isEqualTo("user1@example.com");
        Assertions.assertThat(optionalUser.get().getPassword()).isEqualTo("password1");
        Assertions.assertThat(optionalUser.get().getIsActive()).isTrue();
    }

    @DisplayName("Test para buscar un usuario que no existe por nombre de usuario")
    @Test
    void findByUsername_whenUserDoesNotExist_shouldNotReturnUser() {
        Optional<User> optionalUser = userRepository.findByUsername("user4@example.com");
        Assertions.assertThat(optionalUser).isNotPresent();
    }

    @DisplayName("Test para contar la cantidad de voluntarios")
    @Test
    void countVolunteers_shouldReturnNumberOfVolunteers() {

        Role adoptadorT = roleRepository.save(adoptador);
        Role voluntarioT = roleRepository.save(voluntario);

        Set<Role>roleAdop = new HashSet<>();
        Set<Role>roleVol = new HashSet<>();
        roleAdop.add(adoptadorT);
        roleVol.add(voluntarioT);
        user.setRoles(roleAdop);
        user2.setRoles(roleAdop);
        user3.setRoles(roleVol);

        User userTest1 = userRepository.save(user);
        User userTest2 = userRepository.save(user2);
        User userTest3 = userRepository.save(user3);

        Long count = userRepository.countVolunteers();
        Assertions.assertThat(count).isEqualTo(1); // 1
    }

    @DisplayName("Test para contar la cantidad de adoptadores")
    @Test
    void countAdopts_shouldReturnNumberOfAdopts() {

        Role adoptadorT = roleRepository.save(adoptador);
        Role voluntarioT = roleRepository.save(voluntario);

        Set<Role>roleAdop = new HashSet<>();
        Set<Role>roleVol = new HashSet<>();
        roleAdop.add(adoptadorT);
        roleVol.add(voluntarioT);
        user.setRoles(roleAdop);
        user2.setRoles(roleAdop);
        user3.setRoles(roleVol);

        User userTest1 = userRepository.save(user);
        User userTest2 = userRepository.save(user2);
        User userTest3 = userRepository.save(user3);



        Long count = userRepository.countAdopts();
        Assertions.assertThat(count).isEqualTo(2); // en este caso, hay 2 usuarios con el rol de adoptador creados en el setup
    }
}
