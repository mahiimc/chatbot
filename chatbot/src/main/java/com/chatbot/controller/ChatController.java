package com.chatbot.controller;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatbot.model.ChatMessage;
import com.chatbot.service.ChatService;

import dev.langchain4j.service.TokenStream;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chat/")
public class ChatController {

	@Autowired
	private ChatService chatService;
	
	@PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE,path = "/message")
	public Flux<ServerSentEvent<String>> chat(@RequestBody ChatMessage chatMessage) {
		Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();
        TokenStream tokenStream = chatService.chat(chatMessage.getContent());

        tokenStream.onNext(new Consumer<String>() {
            @Override
            public void accept(String token) {
                sink.tryEmitNext(token);
            }
        }).onComplete((c) -> {
            sink.tryEmitComplete();
        }).onError(error -> {
            sink.tryEmitError(error);
        }).start();

        Flux<String> tokenFlux = sink.asFlux();
        return tokenFlux.filter(line -> !line.isBlank()).map(token -> ServerSentEvent.<String>builder()
                .data(token)
                .build());
	}
}
