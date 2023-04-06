package com.example.adoptame.application.entities.pet.model;

import com.example.adoptame.application.entities.character.model.Character;
import com.example.adoptame.application.entities.color.model.Color;
import com.example.adoptame.application.entities.type.model.Type;
import com.example.adoptame.application.entities.user.model.User;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet")
    private Integer id;

    @NotNull
    @NotBlank
    @Column(nullable = false,columnDefinition = "varchar(100)")
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.]*")
    private String name;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String age;

    @Column(nullable = false)
    private char gender;

    @Column(columnDefinition = "varchar(100)")
    private String breed;

    @Column(nullable = false)
    @Size(min = 10, max = 255)
    private String description;

    @Column(name = "is_adopted", nullable = false, columnDefinition = "tinyint default 0")
    private Boolean isAdopted;

    @Column(name = "is_dropped", nullable = false, columnDefinition = "tinyint default 0")
    private Boolean isDropped;

    @Column(name = "is_active", nullable = false, columnDefinition = "tinyint default 0")
    private Boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_character")
    private Character character;

    @ManyToMany(mappedBy = "favoritePets")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "pet",cascade = CascadeType.PERSIST)
    private Set<PetAdopted>adoptedPets;

    @OneToMany(mappedBy = "pet",cascade = CascadeType.PERSIST)
    private List<PetImage>images;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

}
