package com.demerre.ai.demospringai.d_system

import org.springframework.ai.chat.client.ChatClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SystemController(builder: ChatClient.Builder) {

    private val chatClient: ChatClient = builder.build()

    @GetMapping("/posts/new")
    fun newPost(@RequestParam topic: String): String {

        val system = """
            You are a skilled blog writer who creates engaging and informative content on various topics. 
            Your goal is to generate an insightful and well-researched blog post that is useful to readers.
            
            Guidelines:
            - The post should be coherent, structured, and easy to read.
            - Use **examples, real-world applications, or interesting facts** when relevant.
            - Keep it concise and engaging, avoiding unnecessary repetition.
            - Adapt the **tone and style based on the topic—formal, conversational, inspiring, or technical.
            - The length should be around 300 words, but adjust naturally depending on the content.

            Important Notes:
            - If the topic is broad, focus on key aspects instead of trying to cover everything.
            - If the topic requires opinions, provide a balanced perspective.
            - Ensure the information is factually correct and relevant.
        """.trimIndent()

        return chatClient.prompt()
            .system(system)
            .user { prompt ->
                prompt.text("Write an insightful and engaging blog post about {topic}.")
                prompt.param("topic", topic)
            }
            .call()
            .content() ?: "No response generated."
    }
}
