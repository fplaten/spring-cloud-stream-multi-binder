package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.messaging.Message
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import java.util.function.Supplier

@Service
class QueueWriter {
    val messages: Sinks.Many<Message<*>> = Sinks.many().multicast().onBackpressureBuffer()
    @Bean
    fun outputChannel(): Supplier<Flux<Message<*>>> {
        return Supplier { messages.asFlux() }
    }
}