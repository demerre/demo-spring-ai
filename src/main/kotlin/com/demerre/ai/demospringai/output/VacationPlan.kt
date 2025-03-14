package com.demerre.ai.demospringai.output

import org.springframework.ai.chat.client.ChatClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class VacationPlan(builder: ChatClient.Builder) {

    private val chatClient: ChatClient = builder.build()

    @GetMapping("/vacation/unstructured")
    fun vacationUnstructured(): String {
        return chatClient.prompt()
            .user("What's a good vacation plan while I'm in Montreal CA for 4 days?")
            .call()
            .content() ?: "No response received."
    }

    @GetMapping("/vacation/structured")
    fun vacationStructured(): Itinerary {
        return chatClient.prompt()
            .user("What's a good vacation plan while I'm in Montreal CA for 4 days?")
            .call()
            .entity(Itinerary::class.java) ?: Itinerary(emptyList())
    }

    data class Activity(val activity: String, val location: String, val day: String, val time: String)
    data class Itinerary(val itinerary: List<Activity>)
}