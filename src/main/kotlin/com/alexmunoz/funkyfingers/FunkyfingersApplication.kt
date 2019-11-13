package com.alexmunoz.funkyfingers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FunkyfingersApplication

fun main(args: Array<String>) {
	runApplication<FunkyfingersApplication>(*args)
}
