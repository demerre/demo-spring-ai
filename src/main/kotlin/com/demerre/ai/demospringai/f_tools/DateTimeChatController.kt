package com.demerre.ai.demospringai.f_tools

import org.springframework.ai.chat.client.ChatClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DateTimeChatController(builder: ChatClient.Builder) {

    private val chatClient: ChatClient = builder
        .defaultTools(DateTimeTools())
        .build()

    @GetMapping("/tools")
    fun tools(): String {
        val system = """
                You have access to a tool that provides the current date and time.
                Use it whenever the user asks about dates or times.
            """.trimIndent()

        return chatClient.prompt()
            .system(system)
            .user("What day is today?")
            .call()
            .content() ?: "No response"

    }
}