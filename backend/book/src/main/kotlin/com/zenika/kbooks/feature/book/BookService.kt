package com.zenika.kbooks.feature.book

import com.zenika.kbooks.dto.BookDto
import com.zenika.kbooks.feature.author.AuthorService
import com.zenika.kbooks.rest.PageDto
import com.zenika.kbooks.rest.PaginationDto
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

    @Autowired
    private lateinit var authorClient : AuthorService

    @Transactional(readOnly = true)
    fun getBook(@PathParam("id") id: Long): BookDto {
        // Find book in repository and throw exception if it was not found.
        val book = bookRepository.findById(id).orElseThrow { NotFoundException("Book $id does not exist") }
        book ?: throw NotFoundException("Book $id does not exist")

        val author = authorClient.getAuthor(book.authorId)
        author ?: throw NotFoundException("Author ${book.authorId} not found")

        return BookDtoConverter.convert(Pair(book, author))
    }

    @Transactional(readOnly = true)
    fun getBooks(pagination: PaginationDto): PageDto<BookDto> {
        val page = bookRepository.findAll(pagination.toPageable())

        val booksDtos = page.content.map { book ->
            val author = authorClient.getAuthor(book.id)
            author ?: throw NotFoundException("Author ${book.authorId} not found")
            Pair(book, author)
        }.map { BookDtoConverter.convert(it) }

        return PageDto(booksDtos, page.totalPages, page.totalElements)
    }

    @Transactional
    fun createBook(dto: BookDto): Long? {
        // If author id is null throw exception.
        dto.authorId ?: throw BadRequestException("Author id must not be null")
        // Find author in database and throw exception if it does not exist.
        val author = authorClient.getAuthor(dto.authorId)
                ?: throw BadRequestException("Author ${dto.authorId} does not exist")

        // Create book.
        val book = Book()
        book.title = dto.title
        book.publication = dto.publication
        book.authorId = dto.authorId
        return bookRepository.save(book).id
    }

    @Transactional
    fun updateBook(@PathParam("id") id: Long, dto: BookDto) {
        // Look for book in database and throw exception if it was not found.
        val book = bookRepository.findById(id).orElse(null) ?: throw NotFoundException()

        // Update book.
        book.title = dto.title
        book.publication = dto.publication

        // Update author if is was changed.
        if(dto.authorId != null && dto.authorId == book.authorId) {
            val author = authorClient.getAuthor(dto.authorId) ?: throw BadRequestException()
            book.authorId = dto.authorId
        }

        bookRepository.save(book)
    }

    @Transactional
    fun deleteBook(@PathParam("id") id: Long) {
        val book = bookRepository.findById(id).orElse(null) ?: throw NotFoundException()
        bookRepository.delete(book)
    }
}