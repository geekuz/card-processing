package com.demo.cardprocessing.repository;

import com.demo.cardprocessing.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByUserId(Long userId);
}
