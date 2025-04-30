package com.expense.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseDto {

    private String externalId;

    @JsonProperty(value = "amount")
    @NonNull
    private BigDecimal amount;

    @JsonProperty(value = "user_id")
    private String userId;

    @JsonProperty(value = "merchant")
    private String merchant;

    @JsonProperty(value = "currency")
    private String currency;

    @JsonProperty(value = "created_at")
    private Timestamp createdAt;

    public ExpenseDto(String json){
        try{
            ObjectMapper objMapper = new ObjectMapper();
            objMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            ExpenseDto expenseDto = objMapper.readValue(json, ExpenseDto.class);
            this.externalId = expenseDto.externalId;
            this.amount = expenseDto.amount;
            this.userId = expenseDto.userId;
            this.merchant = expenseDto.merchant;
            this.currency = expenseDto.currency;
            this.createdAt = expenseDto.createdAt;
        }catch (Exception ex){
            throw  new RuntimeException("Failed to deserialize JSON to an ExpenseDto Object", ex);
        }
    }
}
