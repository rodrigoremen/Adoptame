package com.example.adoptame.application.entities.user.model;

import com.example.adoptame.application.repository.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends EntityRepository<User,Long> {
    // devuelve un usuario por username (correo) solo si isActive es true
    Optional<User> findByUsernameAndIsActive(String username, Boolean enabled);
    // busca y devuelva un usario por username (correo)
    Optional<User> findByUsername(String username);


    // consulta que devuelva la cantidad de usuarios con el rol de voluntario
    @Query(
            value = "select COUNT(u.id_user) from users u join authorities a on u.id_user = a.user_id  join roles r on a.rol_id = r.id_rol where u.enabled= 1 and r.authority = 'ROL_VOLUNTEER'",
            nativeQuery = true)
    Long countVolunteers();
    // consulta que devuelve la cantidad de usuarios con el rol de adoptador
    @Query(
            value = "select COUNT(u.id_user) from users u join authorities a on u.id_user = a.user_id  join roles r on a.rol_id = r.id_rol where u.enabled= 1 and r.authority = 'ROL_ADOPTER'",
            nativeQuery = true)
    Long countAdopts();
}
