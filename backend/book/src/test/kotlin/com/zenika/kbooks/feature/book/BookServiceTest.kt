package com.zenika.kbooks.feature.book

import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import com.nhaarman.mockito_kotlin.any
import com.zenika.kbooks.dto.AuthorDto
import com.zenika.kbooks.dto.BookDto
import com.zenika.kbooks.feature.author.AuthorService
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import javax.ws.rs.NotFoundException
import kotlin.test.assertEquals
import kotlin.test.fail

/**
 * Book service test class.
 */
@RunWith(SpringRunner::class)
class BookServiceTest {

    @Mock
    private lateinit var authorService: AuthorService
    @Mock
    private lateinit var bookRepository: IBookRepository
    @InjectMocks
    private lateinit var bookService: BookService

    @Test
    fun testGetBook() {
        /* Given */
        val bookId = 1L
        whenever(bookRepository.findById(bookId)).thenReturn(Optional.of(Book(id = bookId, authorId = 1)))
        whenever(authorService.getAuthor(1)).thenReturn(AuthorDto(id = 1))

        /* When */
        val result = bookService.getBook(bookId)

        /* Then */
        assertEquals(bookId, result.id)
    }

    @Test(expected = NotFoundException::class)
    fun testGetBookWhenBookIsNotFound() {
        /* Given */
        val bookId = 2L
        whenever(bookRepository.findById(bookId)).thenReturn(Optional.empty())

        /* When */
        bookService.getBook(bookId)

        /* Then */
        fail("NotFoundException was not thrown")
    }

    @Test
    fun testCreateBook() {
        /* Given */
        val bookId = 2L
        val authorId = 5L
        whenever(bookRepository.save(any<Book>())).thenReturn(Book(id = bookId))
        whenever(authorService.getAuthor(5)).thenReturn(AuthorDto(id = authorId))

        /* When */
        val result = bookService.createBook(BookDto(authorId = authorId))

        /* Then */
        assertEquals(bookId, result)
    }

    @Test
    fun testUpdateBook() {
        /* Given */
        val bookId = 1L;
        val book = Book(id = bookId)
        whenever(bookRepository.findById(bookId)).thenReturn(Optional.of(book))

        /* When */
        bookService.updateBook(bookId, BookDto())

        /* Then */
        verify(bookRepository).findById(bookId)
    }
}