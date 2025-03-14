package com.demerre.ai.demospringai.g_memory

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor
import org.springframework.ai.chat.memory.InMemoryChatMemory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class StatefulController(builder: ChatClient.Builder) {

    private val chatClient: ChatClient = builder
        .defaultAdvisors(MessageChatMemoryAdvisor(InMemoryChatMemory()))
        .build()


    @GetMapping("/chat")
    fun home(@RequestParam message: String): String {
        return chatClient.prompt()
            .user(message)
            .call()
            .content().orEmpty()
    }
}