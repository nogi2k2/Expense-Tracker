package com.expense.service.consumer;

import org.apache.kafka.common.serialization.Deserializer;
import com.expense.service.dto.ExpenseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ExpenseDeserializer implements Deserializer<ExpenseDto>{
    
    @Override
    public void close(){}

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1){}

    @Override 
    public ExpenseDto deserialize(String arg0, byte[] arg1){
        ExpenseDto expenseDto = null;
        ObjectMapper objMapper = new ObjectMapper();
        try{
            expenseDto = objMapper.readValue(arg1, ExpenseDto.class);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return expenseDto;
    }
}
