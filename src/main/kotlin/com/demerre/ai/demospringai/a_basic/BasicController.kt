package com.demerre.ai.demospringai.a_basic

import org.springframework.ai.chat.client.ChatClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BasicController(builder: ChatClient.Builder) {

    private val chatClient: ChatClient = builder.build()

    @GetMapping("/basic")
    fun tools(): String {

        return chatClient.prompt("What is the biggest city in Europe?").call()
            .content()
            ?: "No response"

    }
}