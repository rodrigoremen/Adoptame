package com.example.adoptame.application.entities.pet.model;

import com.example.adoptame.application.entities.user.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
public class PetAdopted implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_petadopted")
    private Integer id;
    @Column(name = "is_accepted", nullable = false, columnDefinition = "tinyint default 0")
    private Boolean isAccepted;

    @Column(name = "is_canceled", nullable = false, columnDefinition = "tinyint default 0")
    private Boolean isCanceled;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public PetAdopted(Pet pet, User user) {
        this.isCanceled = false;
        this.isAccepted = false;
        this.pet = pet;
        this.user = user;
    }
}
