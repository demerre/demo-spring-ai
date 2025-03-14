package com.demerre.ai.demospringai.image

import org.springframework.ai.chat.client.ChatClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException

@RestController
class ImageDetection(builder: ChatClient.Builder) {

    private val chatClient: ChatClient = builder.build()

    @Value("classpath:/images/test.jpg")
    private lateinit var sampleImage: Resource

    @GetMapping("/image-to-text")
    @Throws(IOException::class)
    fun image(): String {
        return chatClient.prompt()
            .user {
                it.text("Can you please explain what you see in the following image?")
                it.media(MimeTypeUtils.IMAGE_JPEG, sampleImage)
            }
            .call()
            .content().orEmpty()
    }
}