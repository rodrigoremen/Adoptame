package com.example.adoptame.application.entities.color.model;

import com.example.adoptame.application.entities.pet.model.Pet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
public class Color implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_color")
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.]*")
    @Column(nullable = false, unique = true, columnDefinition = "varchar(20)")
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 10)
    @Pattern(regexp = "[A-Za-z0-9#]*")
    @Column(nullable = false, columnDefinition = "varchar(10)")
    private String codeHexadecimal;

    @OneToMany(mappedBy = "color",cascade = CascadeType.PERSIST)
    private Set<Pet>pets;
}
