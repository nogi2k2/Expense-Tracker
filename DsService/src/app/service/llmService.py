import os
from dotenv import load_dotenv
from service.Expense import Expense
from langchain_core.prompts import ChatPromptTemplate
from langchain_mistralai import ChatMistralAI

class LLMService():
    def __init__(self):
        self.prompt = ChatPromptTemplate.from_messages(
        [
            (
                "system",
                "You are an expert extraction algorithm. "
                "Only extract relevant information from the text. "
                "If you do not know the value of an attribute asked to extract, "
                "return null for the attribute's value.",
            ),
            ("human", "{text}")
        ]
        )

        # load_dotenv()
        # self.apiKey = os.getenv("MISTRAL_API_KEY")
        self.apiKey = "ahEgvbV5u8sUsRYpkED02nt8tADIAeXe"
        self.llm = ChatMistralAI(api_key = self.apiKey, model = "mistral-large-latest", temperature = 0)
        self.runnable = self.prompt | self.llm.with_structured_output(schema = Expense)

    def runLLM(self, message):
        return self.runnable.invoke({"text": message})

