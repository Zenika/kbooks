package com.zenika.kbooks

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KbooksApplication

fun main(args: Array<String>) {
    runApplication<KbooksApplication>(*args)
}
