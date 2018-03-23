package com.zenika.kbooks.feature.author

import com.zenika.kbooks.feature.book.Book
import javax.persistence.*

/**
 * JPA Author entity. All parameters have a default value because Hibernate need an empty constructor.
 */
@Entity
@Table(name = "author")
data class Author(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                  var id: Long? = null,
                  var name: String? = null,
                  @OneToMany(mappedBy = "author")
                  var books: List<Book> = mutableListOf())