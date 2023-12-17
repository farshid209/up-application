package org.example.service;

import org.example.entity.Account;
import org.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account getById(Long id){
        return repository.findById(id).get();
    }

    public void create(Account account){
        repository.save(account);
    }

    public void update(Account account){
      Account oldAccount = getById(account.getId());

      oldAccount.setBalance(account.getBalance());
      oldAccount.setActive(account.isActive());

      repository.save(oldAccount);
    }
}
