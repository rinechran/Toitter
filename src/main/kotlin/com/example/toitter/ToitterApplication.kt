package com.example.toitter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ToitterApplication

fun main(args: Array<String>) {
	runApplication<ToitterApplication>(*args)
}
