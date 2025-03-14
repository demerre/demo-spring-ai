package com.demerre.ai.demospringaiuser

import com.demerre.ai.demospringaibasic.BasicApplication
import org.springframework.ai.chat.client.ChatClient
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class UserApplication {

    @Bean
    fun commandLineRunner(builder: ChatClient.Builder): CommandLineRunner {
        return CommandLineRunner {

            val client = builder.build()
            val content = client.prompt()
                .user("I'm visiting Vigo next week, give me the best place to visit and to have lunch I must visit")
                .call().content()

            println(content)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<BasicApplication>(*args)
}
