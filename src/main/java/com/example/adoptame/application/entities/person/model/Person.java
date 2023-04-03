package com.example.adoptame.application.entities.person.model;

import com.example.adoptame.application.entities.address.model.Address;
import com.example.adoptame.application.entities.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private Integer id;
    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.]*")
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String firstName;

    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.]*")
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String lastName;

    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.]*")
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String surName;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.]*")
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String telephone;

    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.]*")
    @Column(nullable = true, columnDefinition = "varchar(255)")
    private String imageProfileUrl;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id_user")
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
