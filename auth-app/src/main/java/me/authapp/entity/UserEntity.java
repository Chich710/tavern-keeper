package me.authapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "create_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "deleted_at")
    private LocalDate deletedAt;

    public UserEntity() {}

    public UserEntity(
            String name,
            String login,
            String password,
            LocalDate createdAt,
            LocalDate deletedAt
    ) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = LocalDate.now();
        this.deletedAt = deletedAt;
    }
}


