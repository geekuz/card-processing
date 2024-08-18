package com.demo.cardprocessing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "cards")
@Getter
@Setter
public class Card implements Serializable {

    @Serial
    private static final long serialVersionUID = 8702289724414110918L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String status;
    private Double balance;
    private String currency;

    // Getters and Setters
}
