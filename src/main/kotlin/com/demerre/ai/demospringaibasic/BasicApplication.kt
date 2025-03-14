package com.demerre.ai.demospringaibasic

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.ai.chat.client.ChatClient
import org.springframework.boot.runApplication

@SpringBootApplication
class BasicApplication {

    @Bean
    fun commandLineRunner(builder: ChatClient.Builder): CommandLineRunner {
        return CommandLineRunner {

            val client = builder.build()
            val content = client.prompt("What is the biggest city in Europe?").call().content()

            println(content)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<BasicApplication>(*args)
}
