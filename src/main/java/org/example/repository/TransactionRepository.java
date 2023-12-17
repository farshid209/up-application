package org.example.repository;

import org.example.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query(value = "select * from transaction where creat_date between :fromDate and :toDate" , nativeQuery = true)
    List<Transaction> findAllByDate(@Param("fromDate") LocalDateTime fromDate,@Param("toDate") LocalDateTime toDate);
}
