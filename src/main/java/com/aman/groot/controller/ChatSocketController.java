package com.aman.groot.controller;

import com.aman.groot.dto.ChatMessage;
import com.aman.groot.entity.Message;
import com.aman.groot.repository.MessageRepository;
import com.aman.groot.service.AIService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatSocketController {

    private final MessageRepository messageRepository;
    private final AIService aiService;

    public ChatSocketController(MessageRepository messageRepository, AIService aiService) {
        this.messageRepository = messageRepository;
        this.aiService = aiService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage handleMessage(ChatMessage chatMessage) {

        // save user message
        messageRepository.save(
                new Message("USER", chatMessage.getContent())
        );

        // generate groot reply
        String reply = aiService.generateReply(chatMessage.getContent());

        messageRepository.save(
                new Message("GROOT", reply)
        );

        return new ChatMessage("GROOT", reply);
    }
}
