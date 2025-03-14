package com.demerre.ai.demospringai.c_asyncuser

import org.springframework.ai.chat.client.ChatClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class AsyncUserController(builder: ChatClient.Builder) {

    private val chatClient: ChatClient = builder.build()

    @GetMapping("/stream-user")
    fun tools(): Flux<String> {
        return chatClient.prompt()
            .user("Count from 1 to 30, just only those numbers")
            .stream()
            .content()
    }
}
