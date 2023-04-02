package com.example.adoptame.application.entities.address.model;

import com.example.adoptame.application.entities.person.model.Person;
import com.example.adoptame.application.repository.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AddressRepository extends EntityRepository<Address,Long> {
    Optional<Address> findByPerson(Person person);
    @Query(value = "SELECT * FROM address ORDER BY id_address DESC LIMIT 1;",nativeQuery = true)
    Optional<Address>findLastAddress();
}
