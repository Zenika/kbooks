package com.zenika.kbooks.feature.book

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

/**
 * Repository to access books.
 */
@Repository
interface IBookRepository : PagingAndSortingRepository<Book, Long>