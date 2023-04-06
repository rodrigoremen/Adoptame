package com.example.adoptame.application.entities.address.model;

import com.example.adoptame.application.entities.person.model.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private long id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-./#]*")
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String street;

    @Pattern(regexp = "[0-9]*")
    @Column(name="internal_number", columnDefinition = "varchar(5)")
    private String internalNumber;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 5)
    @Pattern(regexp = "[0-9]*")
    @Column(name="external_number", nullable = false, columnDefinition = "varchar(5)")
    private String externalNumber;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 5)
    @Pattern(regexp = "[0-9]*")
    @Column(name="zip_code", nullable = false, columnDefinition = "varchar(50)")
    private String zipCode;

    @Pattern(regexp = "[A-Za-zÀ-ÿ '-./#]*")
    @Column(name="suburb", columnDefinition = "varchar(128)")
    private String suburb;

    @Pattern(regexp = "[A-Za-zÀ-ÿ '-./#]*")
    @Column(name="town", columnDefinition = "varchar(128)")
    private String town;
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-./#]*")
    @Column(name="state", columnDefinition = "varchar(128)")
    private String state;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "address")
    private Person person;
}

