package com.expense.service.consumer;

import com.expense.service.dto.ExpenseDto;
import com.expense.service.service.ExpenseService;

import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExpenseConsumer {

    private ExpenseService expenseService;

    @Autowired
    ExpenseConsumer(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId="${spring.kafka.consumer.group-id}")
    public void consume(ExpenseDto expenseDto){
        try{
            expenseService.createExpense(expenseDto);
        }catch (Exception ex){
            System.out.println("ExpenseServiecConsumer: Error in consuming Kafka Event");
            ex.printStackTrace();
        }
    }
}
