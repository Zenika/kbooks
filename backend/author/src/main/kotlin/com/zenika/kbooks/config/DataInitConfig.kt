package com.zenika.kbooks.config

import com.zenika.kbooks.feature.author.Author
import com.zenika.kbooks.feature.author.IAuthorRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Insert default data to repository.
 */
@Configuration
class DataInitConfig {

    @Bean
    fun init(authorRepository: IAuthorRepository) = CommandLineRunner {
        val stephenKing = Author(name = "Stephen King")
        authorRepository.save(stephenKing)

        val robinHobb = Author(name = "Robin Hobb")
        authorRepository.save(robinHobb)
    }
}