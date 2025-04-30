package com.expense.service.service;

import com.expense.service.dto.ExpenseDto;
import com.expense.service.entities.Expense;
import com.expense.service.repository.ExpenseRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import lombok.AllArgsConstructor; 
import org.apache.logging.log4j.util.Strings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class ExpenseService {
    
    private final ExpenseRepository expenseRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    private void setCurrency(ExpenseDto expenseDto){
        if (Objects.isNull(expenseDto.getCurrency())){
            expenseDto.setCurrency("inr");
        }
    }

    public boolean createExpense(ExpenseDto expenseDto){
        setCurrency(expenseDto);
        try{
            expenseRepository.save(objectMapper.convertValue(expenseDto, Expense.class));
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean updateExpense(ExpenseDto expenseDto){
        setCurrency(expenseDto);
        Optional<Expense> expenseOpt = expenseRepository.findByUserIdAndExternalId(expenseDto.getUserId(), expenseDto.getExternalId());
        if (expenseOpt.isEmpty()){
            return false;
        }
        Expense expense = expenseOpt.get();
        expense.setCurrency(Strings.isNotBlank(expenseDto.getCurrency())? expenseDto.getCurrency(): expense.getCurrency());
        expense.setMerchant(Strings.isNotBlank(expenseDto.getMerchant())? expenseDto.getMerchant(): expense.getMerchant());
        expense.setAmount(expenseDto.getAmount());
        expenseRepository.save(expense);
        return true;
    }

    public List<ExpenseDto> getExpenses(String userId){
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        return objectMapper.convertValue(expenses, new TypeReference<List<ExpenseDto>> (){});
    }
}
