package com.example.adoptame.application.entities.user.controller;

import com.example.adoptame.application.entities.person.model.PersonRepository;
import com.example.adoptame.application.entities.role.controller.RoleService;
import com.example.adoptame.application.entities.role.model.Role;
import com.example.adoptame.application.entities.role.model.RoleRepository;
import com.example.adoptame.application.entities.user.model.User;
import com.example.adoptame.application.entities.user.model.UserRepository;
import com.example.adoptame.infrestucture.Twilio.Controller.SmsService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.MessagingException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SmsService smsService;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public Optional<User> save(User entity) {
        return Optional.of(userRepository.save(entity));
    }

    @Transactional
    public Optional<User> addUser(User user) {
       User userSave = userRepository.save(user);
       return Optional.of(userSave);
    }

    @Transactional
    public void addRole(User user, Role role) {

        roleRepository.save(role);
    }

    @Transactional
    public Optional<User> update(User entity) {
        Optional<User> updatedEntity;
        updatedEntity = userRepository.findById(entity.getId());
        if (!updatedEntity.isEmpty())
            userRepository.save(entity);
        return updatedEntity;
    }












}
