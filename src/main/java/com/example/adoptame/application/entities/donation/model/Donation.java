package com.example.adoptame.application.entities.donation.model;

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

@Entity
@Table(name = "donations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donation implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_donation")
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    @Column(nullable = false, unique = true, columnDefinition = "varchar(20)")
    private String quantity;

    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.]*")
    @Column(columnDefinition = "varchar(50)")
    private String status;

    @Column(columnDefinition = "varchar(150)")
    private boolean isSucces;

    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.]*")
    @Column(columnDefinition = "varchar(150)")
    private String authorization;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
