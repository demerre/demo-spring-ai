package com.demerre.ai.demospringai.f_tools

import org.springframework.ai.tool.annotation.Tool
import org.springframework.context.i18n.LocaleContextHolder
import java.time.LocalDateTime

class DateTimeTools {
    @Tool(description = "Get the current date and time in the user's timezone")
    fun getCurrentDateTime(): String {
        val dateTime = LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString()
        println("Executing getCurrentDateTime: $dateTime")
        return dateTime
    }
}