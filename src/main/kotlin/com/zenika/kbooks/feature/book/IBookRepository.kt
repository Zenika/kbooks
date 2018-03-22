package com.zenika.kbooks.feature.book

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IBookRepository : CrudRepository<Book, Long>