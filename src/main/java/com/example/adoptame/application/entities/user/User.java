package com.example.adoptame.application.entities.user;

import com.example.adoptame.application.entities.color.Donnation;
import com.example.adoptame.application.entities.person.Person;
import com.example.adoptame.application.entities.role.Role;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 50)
    @Email
    @Column(unique = true, nullable = false, columnDefinition = "varchar(50)")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 100)
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String password;

    @Column(nullable = false, columnDefinition = "tinyint default 1")
    private Boolean active;

    @Column
    @OneToOne(mappedBy = "user")
    private Person person;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
    @Valid
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "permmissions", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Donnation> donnationList;

    public void addRoles() {
        roles = new HashSet<>();
    }

    public void setRoles() {
        this.roles = roles;
    }


}
