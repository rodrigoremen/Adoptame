package com.example.adoptame.application.entities.character.model;

import com.example.adoptame.application.entities.pet.model.Pet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "characters")
public class Character implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_character")
    private long id;

    @NotNull
    @NotBlank
    @Size(min = 4,max =30)
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.]*")
    @Column(nullable = false, unique = true, columnDefinition = "varchar(20)")
    private String name;

    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.]*")
    @Column
    private String description;
    @Column(columnDefinition = "tinyint default 1")
    private Boolean status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "character",cascade = CascadeType.PERSIST)
    private Set<Pet>pets;

    public Character(String name) {
        this.name = name;
        this.status = true;
    }
}
