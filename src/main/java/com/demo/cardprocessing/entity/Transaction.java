package com.demo.cardprocessing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction implements Serializable {
    @Serial
    private static final long serialVersionUID = -3524304647606922410L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    private Double amount;
    private String type;
    private LocalDateTime timestamp;

    // Getters and Setters
}