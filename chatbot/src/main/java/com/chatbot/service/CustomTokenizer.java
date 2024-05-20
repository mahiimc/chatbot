package com.chatbot.service;

import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.Tokenizer;

public class CustomTokenizer implements Tokenizer {

	@Override
	public int estimateTokenCountInToolSpecifications(Iterable<ToolSpecification> toolSpecifications) {
		return 0;
	}

	@Override
	public int estimateTokenCountInToolExecutionRequests(Iterable<ToolExecutionRequest> toolExecutionRequests) {
		return 0;
	}

	@Override
	public int estimateTokenCountInText(String text) {
		return 0;
	}

	@Override
	public int estimateTokenCountInMessages(Iterable<ChatMessage> messages) {
		return 0;
	}

	@Override
	public int estimateTokenCountInMessage(ChatMessage message) {
		return 0;
	}

}
