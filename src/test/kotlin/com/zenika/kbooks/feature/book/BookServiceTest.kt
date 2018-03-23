package com.zenika.kbooks.feature.book

import com.zenika.kbooks.feature.author.Author
import com.zenika.kbooks.feature.author.IAuthorRepository
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import javax.ws.rs.NotFoundException

@RunWith(SpringRunner::class)
class BookServiceTest {

    @Mock
    private lateinit var IAuthorRepository: IAuthorRepository
    @Mock
    private lateinit var bookRepository: IBookRepository
    @InjectMocks
    private lateinit var bookService: BookService

    @Test
    fun testGetBook() {
        val book = Book(id = 1)
        Mockito.`when`(bookRepository.findById(1)).thenReturn(Optional.of(book))

        val result = bookService.getBook(1)

        Assert.assertEquals(book.id, result.id)
    }

    @Test
    fun testGetBookNotFound() {
        Mockito.`when`(bookRepository.findById(1)).thenReturn(Optional.empty())

        try {
            bookService.getBook(1)
            Assert.fail("Not found exception must have been thrown")
        } catch(e: NotFoundException) {
            // We expect this exception.
        }
    }

    @Test
    fun testCreateBook() {
        val bookId = 2L
        Mockito.`when`(bookRepository.save(Mockito.any(Book::class.java))).thenReturn(Book(id = bookId))
        Mockito.`when`(IAuthorRepository.findById(5)).thenReturn(Optional.of(Author(id = 5)))

        val result = bookService.createBook(BookDto(authorId = 5))
        Assert.assertEquals(bookId, result)
    }

    @Test
    fun testUpdateBook() {
        val bookId = 1L;
        val book = Book(id = bookId)
        Mockito.`when`(bookRepository.findById(bookId)).thenReturn(Optional.of(book))

        bookService.updateBook(bookId, BookDto())

        Mockito.verify(bookRepository).findById(bookId)
    }
}