package com.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatbot.model.Assistant;

import dev.langchain4j.service.TokenStream;

@Service
public class ChatService {

	@Autowired
	private Assistant assistant;
	
	public TokenStream chat(String userMessage) {
		TokenStream response = assistant.chat("1", userMessage);
		return response;

	}
	
}
