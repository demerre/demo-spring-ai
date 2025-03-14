package com.demerre.ai.demospringai.h_image

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.ollama.api.OllamaOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException

@RestController
class ImageDetectionController(builder: ChatClient.Builder) {

    private val chatClient = builder.build() // default mistral

    @Value("classpath:/images/test.jpg")
    private lateinit var sampleImage: Resource

    @GetMapping("/image-to-text")
    @Throws(IOException::class)
    fun image(): String {
        val ollamaOptions = OllamaOptions.builder()
            .model("llava") // https://ollama.com/library/llava
            .temperature(0.3)
            .build()

        return chatClient.prompt()
            .user {
                it.text("Describe the image in detail.")
                it.media(MimeTypeUtils.IMAGE_JPEG, sampleImage)
            }
            .options(ollamaOptions)
            .call()
            .content().orEmpty()
    }
}
