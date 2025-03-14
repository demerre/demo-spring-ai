package com.demerre.ai.demospringai.e_entity

import org.springframework.ai.chat.client.ChatClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EntityBookController(builder: ChatClient.Builder) {

    private val chatClient: ChatClient = builder.build()

    @GetMapping("/books/structured")
    fun bookStructured(): BookRecommendations {
        return chatClient.prompt()
            .user("Can you recommend three good books to read?")
            .call()
            .entity(BookRecommendations::class.java) ?: BookRecommendations(emptyList())
    }

    data class Book(val title: String, val author: String, val genre: String)
    data class BookRecommendations(val recommendations: List<Book>)
}
