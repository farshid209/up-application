package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_account_id")
    private Account from;

    @ManyToOne
    @JoinColumn(name = "to_account_id")
    private Account destination;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "creat_date")
    private LocalDateTime creatDate;
}
