package com.example.toitter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class ToitterApplication

fun main(args: Array<String>) {
	runApplication<ToitterApplication>(*args)
}
