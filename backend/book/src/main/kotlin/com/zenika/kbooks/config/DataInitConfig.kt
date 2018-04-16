package com.zenika.kbooks.config

import com.zenika.kbooks.feature.book.Book
import com.zenika.kbooks.feature.book.IBookRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate

/**
 * Insert default data to repository.
 */
@Configuration
class DataInitConfig {

    @Bean
    fun init(bookRepository: IBookRepository) = CommandLineRunner {
        bookRepository.save(Book(
                title = "22/11/63",
                publication = LocalDate.parse("2011-11-08"),
                authorId = 1
        ))
        bookRepository.save(Book(
                title = "L'assassin Royal",
                publication = LocalDate.parse("1998-12-17"),
                authorId = 2
        ))
    }
}