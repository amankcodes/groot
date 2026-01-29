package com.aman.groot.controller;

import com.aman.groot.entity.Message;
import com.aman.groot.repository.MessageRepository;
import com.aman.groot.service.AIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final MessageRepository messageRepository;
    private final AIService aiService;

    public ChatController(MessageRepository messageRepository, AIService aiService) {
        this.messageRepository = messageRepository;
        this.aiService = aiService;
    }

    @PostMapping("/send")
    public Message sendMessage(@RequestParam String text) {

        Message userMsg = new Message("USER", text);
        messageRepository.save(userMsg);

        String reply = aiService.generateReply(text);
        Message botMsg = new Message("GROOT", reply);
        messageRepository.save(botMsg);

        return botMsg;
    }
}
