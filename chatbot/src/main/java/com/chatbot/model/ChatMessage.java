package com.chatbot.model;

import java.io.Serializable;

public class ChatMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	private String sender;
	private String content;
//	private String sessionId;

	public ChatMessage() {

	}

	/**
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

**/
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	/**

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
**/
}
