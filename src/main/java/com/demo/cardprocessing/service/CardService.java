package com.demo.cardprocessing.service;

import com.demo.cardprocessing.entity.Card;
import com.demo.cardprocessing.entity.User;
import com.demo.cardprocessing.repository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard(User user, String currency) {
        Card card = new Card();
        card.setUser(user);
        card.setCurrency(currency);
        card.setBalance(0.0);
        card.setStatus("ACTIVE");
        return cardRepository.save(card);
    }

    public void blockCard(Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow();
        card.setStatus("BLOCKED");
        cardRepository.save(card);
    }

    // Additional methods...
}
