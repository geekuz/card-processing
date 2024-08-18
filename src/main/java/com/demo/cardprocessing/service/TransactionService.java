package com.demo.cardprocessing.service;

import com.demo.cardprocessing.entity.Card;
import com.demo.cardprocessing.entity.Transaction;
import com.demo.cardprocessing.exception.InsufficientFundsException;
import com.demo.cardprocessing.repository.CardRepository;
import com.demo.cardprocessing.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;

    public TransactionService(TransactionRepository transactionRepository, CardRepository cardRepository) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
    }

    public Transaction withdraw(Long cardId, Double amount) {
        Card card = cardRepository.findById(cardId).orElseThrow();
        if (card.getBalance() < amount) {
            throw new InsufficientFundsException();
        }
        card.setBalance(card.getBalance() - amount);
        cardRepository.save(card);

        Transaction transaction = new Transaction();
        transaction.setCard(card);
        transaction.setAmount(amount);
        transaction.setType("WITHDRAWAL");
        transaction.setTimestamp(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    // Additional methods...
}