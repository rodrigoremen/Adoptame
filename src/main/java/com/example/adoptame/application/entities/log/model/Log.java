package com.example.adoptame.application.entities.log.model;

import com.example.adoptame.application.entities.user.model.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Log implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Integer id;

    @Column(name="old_data", columnDefinition = "TEXT")
    private String oldData;

    @Column(name="new_data", columnDefinition = "TEXT")
    private String newData;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "enum ('Crear', 'Actualizar', 'Eliminar')")
    private String action;

    @Column(nullable = false, columnDefinition = "enum ('address', 'characters', 'colors', 'donations', 'favorites_pets', 'logs','news', 'pets', 'pets_adopted', 'pet_images', 'profile', 'request','roles', 'sizes', 'tags', 'tags_news','types', 'users' )")
    private String board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
