package com.demo.cardprocessing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "authorities")
@Getter
@Setter
public class AuthorityEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -7578864121331966455L;
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, length = 20)
    private String name;
    @ManyToMany(mappedBy = "authorities")
    private Collection<RoleEntity> roles;

    public AuthorityEntity(String name) {
        this.name = name;
    }

    public AuthorityEntity() {

    }
}
