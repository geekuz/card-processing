package com.demo.cardprocessing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -4175691244635454248L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String encryptedPassword;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(referencedColumnName = "id", name = "users_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "roles_id"))
    private Collection<RoleEntity> roles;
}
