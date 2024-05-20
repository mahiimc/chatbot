package com.chatbot.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.chatbot.model.Assistant;
import com.chatbot.service.CustomTokenizer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.service.AiServices;

@Configuration
public class AppConfig {

	
	@Bean
	public StreamingChatLanguageModel getModel() {
		return  OllamaStreamingChatModel.builder()
				.baseUrl("http://localhost:11434")
				.modelName("phi3")
				.temperature(0.5)
				.timeout(Duration.ofSeconds(60))
				.build();
	}
	
	
	@Bean
	public ChatMemory getChatMemory() {
		return TokenWindowChatMemory.builder()
				.id(1)
				.maxTokens(1000, new CustomTokenizer())
				.build();
	}
	
	@Bean
	public Assistant getAssistant() {
		return AiServices.builder(Assistant.class)
				.streamingChatLanguageModel(getModel())
				.chatMemoryProvider(new ChatMemoryProvider() {	
					@Override
					public ChatMemory get(Object memoryId) {
						return getChatMemory();
					}
				})
				.build();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}
	
}
