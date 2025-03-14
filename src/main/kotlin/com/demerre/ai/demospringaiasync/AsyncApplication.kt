package com.demerre.ai.demospringaiasync

import com.demerre.ai.demospringaibasic.BasicApplication
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.ai.chat.client.ChatClient
import org.springframework.boot.runApplication

@SpringBootApplication
class AsyncApplication {

    @Bean
    fun commandLineRunner(builder: ChatClient.Builder): CommandLineRunner {
        return CommandLineRunner {
            val client = builder.build()

            client.prompt()
                .user("Count from 1 to 10, just only those numbers")
                .stream()
                .content()
                .subscribe {
                        response -> println(response)
                }

        }
    }
}

fun main(args: Array<String>) {
    runApplication<BasicApplication>(*args)
}
