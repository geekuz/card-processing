package com.demo.cardprocessing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 3192581923592683204L;
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, length = 20)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<UserEntity> userEntities;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "roles_authorities", joinColumns = @JoinColumn(referencedColumnName = "id", name = "roles_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "authorities_id"))
    private Collection<AuthorityEntity> authorities;

    public RoleEntity(String name, Collection<AuthorityEntity> authorities) {
        this.name = name;
        this.authorities = authorities;
    }

    public RoleEntity() {

    }
}
