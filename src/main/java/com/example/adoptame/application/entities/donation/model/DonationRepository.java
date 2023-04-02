package com.example.adoptame.application.entities.donation.model;

import com.example.adoptame.application.repository.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DonationRepository extends EntityRepository<Donation,Long> {
    @Query(value = "SELECT SUM(d.quantity) FROM donation d WHERE d.is_completed = 1;",
            nativeQuery = true)
    Double sumCuantity();

    @Query(
            value = "SELECT * FROM donation d WHERE d.is_succes = 1 ORDER BY d.created_at LIMIT 5 ",
            nativeQuery = true)
    List<Donation> findTop5ByCreatedAtDesc();

    @Query(value = "SELECT SUM(d.quantity) FROM donation d WHERE d.is_succes = 1 AND d.user_id=?1",
            nativeQuery = true)
    Double sumCuantityByUserId(Integer userid);
}
