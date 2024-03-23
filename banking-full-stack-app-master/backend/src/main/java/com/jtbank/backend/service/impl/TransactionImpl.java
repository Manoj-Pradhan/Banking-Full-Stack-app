package com.jtbank.backend.service.impl;

import com.jtbank.backend.model.Transaction;
import com.jtbank.backend.repository.AccountRepository;
import com.jtbank.backend.repository.TransactionRepository;
import com.jtbank.backend.service.IAccountService;
import com.jtbank.backend.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class TransactionImpl implements ITransactionService {
    private  final TransactionRepository repository;
    private final AccountRepository accountRepository;

    @Async
    @Override
    public void addTransaction(Transaction transaction, long accountNumber) {
        System.out.println(Thread.currentThread().getName());
        var account = accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        var transactions = account.getTransactions();

        if (transactions == null){
            transactions = new ArrayList<>();

        }
        transaction.setTimestamp(LocalDateTime.now());
        transactions.add(transaction);

        repository.save(transaction);
    }
    }
