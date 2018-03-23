package com.zenika.kbooks.feature.author

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface IAuthorRepository : PagingAndSortingRepository<Author, Long>