package com.zenika.kbooks.feature.book

import com.zenika.kbooks.util.rest.PageDto
import com.zenika.kbooks.util.rest.PaginationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.ws.rs.*

/**
 * Book service class.
 */
@Service
class BookService {
    @Autowired
    private lateinit var bookRepository: IBookRepository

    @Transactional(readOnly = true)
    fun getBook(@PathParam("id") id: Long): BookDto {
        return bookRepository.findById(id)
                .map { BookDtoConverter().convert(it) }
                .orElseThrow(::NotFoundException)
    }

    @Transactional(readOnly = true)
    fun getBooks(pagination: PaginationDto): PageDto<BookDto> {
        var page = bookRepository.findAll(pagination.toPageable())
        return BookDtoConverter().convert(page)
    }

    @Transactional
    fun createBook(dto: BookDto): Long? {
        val book = Book()
        book.title = dto.title
        book.publication = dto.publication
        book.author = dto.author

        return bookRepository.save(book).id
    }

    @Transactional
    fun updateBook(@PathParam("id") id: Long, dto: BookDto) {
        val book = bookRepository.findById(id).orElseThrow(::NotFoundException)
        book.title = dto.title
        book.publication = dto.publication
        book.author = dto.author
        bookRepository.save(book)
    }

    @Transactional
    fun deleteBook(@PathParam("id") id: Long) {
        val book = bookRepository.findById(id).orElseThrow(::NotFoundException)
        bookRepository.delete(book)
    }
}