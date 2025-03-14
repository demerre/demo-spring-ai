package com.demerre.ai.demospringai.g_memory

import org.springframework.ai.chat.client.ChatClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class StatelessController(builder: ChatClient.Builder) {

    private val chatClient: ChatClient = builder
        .build()


    @GetMapping("/dumb-chat")
    fun dumbHome(@RequestParam message: String): String {
        return chatClient.prompt()
            .user(message)
            .call()
            .content().orEmpty()
    }
}