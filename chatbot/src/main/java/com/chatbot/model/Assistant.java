package com.chatbot.model;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

public interface Assistant {
	
	@SystemMessage("You are a polite chatbot of a company called IMC Technologies.")
	TokenStream chat(@MemoryId String memoryId , @UserMessage String message);

}
