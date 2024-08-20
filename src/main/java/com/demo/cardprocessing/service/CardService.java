package com.demo.cardprocessing.service;

import com.demo.cardprocessing.entity.Card;
import com.demo.cardprocessing.entity.Currency;
import com.demo.cardprocessing.entity.Status;
import com.demo.cardprocessing.entity.UserEntity;
import com.demo.cardprocessing.repository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard(UserEntity userEntity, String currency) {
        Card card = new Card();
        card.setUserEntity(userEntity);
        card.setCurrency(Currency.UZS);
        card.setBalance(0.0);
        card.setStatus(Status.ACTIVE);
        return cardRepository.save(card);
    }

    public void blockCard(Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow();
        card.setStatus(Status.BLOCKED);
        cardRepository.save(card);
    }

    // Additional methods...
}
