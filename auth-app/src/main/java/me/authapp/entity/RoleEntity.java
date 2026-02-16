package me.authapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "role_info")
@Getter
@Setter
public class RoleEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "roleEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserEntity> userEntity;
}