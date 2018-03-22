package com.zenika.kbooks.feature.book

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.ws.rs.*

@Service
class BookService {
    @Autowired
    private lateinit var bookRepository: IBookRepository

    fun getBook(@PathParam("id") id: Long): BookDto {
        return bookRepository.findById(id)
                .map { BookDtoConverter().convert(it) }
                .orElseThrow(::NotFoundException)
    }

    fun getBooks(): List<BookDto> = bookRepository.findAll().map { BookDtoConverter().convert(it) }

    fun createBook(dto: BookDto): Long? {
        val book = Book(

        )
        book.title = dto.title
        book.publication = dto.publication
        book.author = dto.author

        return bookRepository.save(book).id
    }

    fun updateBook(@PathParam("id") id: Long, dto: BookDto) {
        val book = bookRepository.findById(id).orElseThrow(::NotFoundException)
        book.title = dto.title
        book.publication = dto.publication
        book.author = dto.author
        bookRepository.save(book)
    }

    fun deleteBook(@PathParam("id") id: Long) {
        val book = bookRepository.findById(id).orElseThrow(::NotFoundException)
        bookRepository.delete(book)
    }
}