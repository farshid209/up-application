package org.example.controller;


import org.example.dto.PayDto;
import org.example.dto.SearchDto;
import org.example.dto.TransferDto;
import org.example.entity.Transaction;
import org.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/search")
    public List<Transaction> search(@RequestBody SearchDto searchDto){
        return service.search(searchDto.getFrom(), searchDto.getTo());
    }

    @PostMapping("/withdraw")
    public boolean withdraw(@RequestBody PayDto dto){
        return service.withdraw(dto.getAccountId(), dto.getAmount());
    }

    @PostMapping("/deposit")
    public boolean deposit(@RequestBody PayDto dto){
        return service.deposit(dto.getAccountId(), dto.getAmount());
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferDto dto) throws Exception {
        service.moneyTransfer(dto.getFromAccountId(),dto.getToAccountId(), dto.getAmount());
    }
}
