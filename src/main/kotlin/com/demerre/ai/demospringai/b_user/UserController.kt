package com.demerre.ai.demospringai.b_user

import org.springframework.ai.chat.client.ChatClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(builder: ChatClient.Builder) {

    private val chatClient: ChatClient = builder.build()

    @GetMapping("/user")
    fun tools(): String {

        return chatClient.prompt()
            .user("I'm visiting Vigo next week, give me the best place to visit and to have lunch I must visit")
            .call().content()
            ?: "No response"

    }
}