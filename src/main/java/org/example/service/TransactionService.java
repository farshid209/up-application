package org.example.service;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repository;
    private final AccountService accountService;

    @Autowired
    public TransactionService(TransactionRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    public void create(Transaction transaction) {
        repository.save(transaction);
    }

    public synchronized boolean withdraw(Long accountId, BigDecimal amount) {
        Account account = accountService.getById(accountId);
        if (account.getBalance().compareTo(amount) < 0) {
            return false;
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);
        accountService.update(account);

        Transaction transaction = new Transaction();
        transaction.setFrom(account);
        transaction.setAmount(amount);
        transaction.setCreatDate(LocalDateTime.now());
        create((transaction));

        return true;
    }

    public synchronized boolean deposit(Long acountId, BigDecimal amount) {
        Account account = accountService.getById(acountId);

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        accountService.update(account);

        Transaction transaction = new Transaction();
        transaction.setDestination(account);
        transaction.setAmount(amount);
        transaction.setCreatDate(LocalDateTime.now());
        create(transaction);
        return true;
    }

    public void moneyTransfer(Long fromAccountId, Long destionAccountId, BigDecimal amount) throws Exception {
        boolean check = withdraw(fromAccountId, amount);
        if (check) {
            deposit(destionAccountId, amount);
        } else {
            throw new Exception("Account balance is less than amount for transfer!");
        }

    }

    public List<Transaction> search(LocalDateTime fromData, LocalDateTime toData){
        return repository.findAllByDate(fromData, toData);
    }
}
