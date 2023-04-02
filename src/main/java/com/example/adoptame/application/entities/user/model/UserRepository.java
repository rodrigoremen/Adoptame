package com.example.adoptame.application.entities.user.model;

import com.example.adoptame.application.repository.EntityRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends EntityRepository<User,Long> {
    Optional<User> findByUsernameAndEnabled(String username, Boolean enabled);
    Optional<User> findByUsername(String username);
    Optional<User> findByLinkRestorePassword(String token);
    Optional<User> findByLinkActivateUsername(String token);

    @Query(
            value = "select COUNT(u.id_user) from users u join authorities a on u.id_user = a.user_id  join roles r on a.rol_id = r.id_rol where u.enabled= 1 and r.authority = 'ROL_VOLUNTEER'",
            nativeQuery = true)
    Long countVolunteers();
    @Query(
            value = "select COUNT(u.id_user) from users u join authorities a on u.id_user = a.user_id  join roles r on a.rol_id = r.id_rol where u.enabled= 1 and r.authority = 'ROL_ADOPTER'",
            nativeQuery = true)
    Long countAdopts();
}
