package com.expense.service.repository;

import com.expense.service.entities.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long>{

    List<Expense> findByUserId(String userId);

    List<Expense> findByUserIdAndCreatedAtBetween(String userId, Timestamp startTime, Timestamp endTime);

    Optional<Expense> findByUserIdAndExternalId(String userId, String externalId);
}
