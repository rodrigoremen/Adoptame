package com.example.adoptame.application.entities.person.model;

import com.example.adoptame.application.entities.address.model.Address;
import com.example.adoptame.application.entities.user.model.User;
import com.example.adoptame.application.repository.EntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PersonRepository extends EntityRepository<Person,Long> {
    Optional<Person> findByUser(User user);
    Optional<Person> findByAddress(Address address);
    List<Person> findAllByUser_Enabled(Boolean enabled);
}
