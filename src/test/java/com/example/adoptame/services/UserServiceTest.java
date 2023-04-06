package com.example.adoptame.services;

import static org.mockito.BDDMockito.given;

import com.example.adoptame.application.entities.role.model.Role;
import com.example.adoptame.application.entities.user.controller.UserService;
import com.example.adoptame.application.entities.user.model.User;
import com.example.adoptame.application.entities.user.model.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private User user;
    private User user2;
    private User user3;
    private Role adoptador;
    private Role voluntario;

    @BeforeEach
    void setUp(){
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


    @DisplayName("Test para guardar un usuario")
    @Test
    void testSaveUser(){
        //given
        given(userRepository.save(user)).willReturn(user);
        //when
        Optional<User> userSave = userService.save(user);
        //then
        Assertions.assertThat(userSave).isNotNull();

    }
}
