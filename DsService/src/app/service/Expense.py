from typing import Optional
from langchain_mistralai import ChatMistralAI
from langchain_core.pydantic_v1 import BaseModel, Field

class Expense(BaseModel):
    """Information about a Transaction made on any Card"""
    amount: Optional[str] = Field(title = "expense", description = "Expense made in the transaction")
    merchant: Optional[str] = Field(title = "merchant", description = "Merchant name for who the transaction was made")
    currency: Optional[str] = Field(title = "currency", description = "Cuurency in which the transaction was made in")