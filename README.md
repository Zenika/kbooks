# KBooks : My first Spring Boot 2 application with kotlin.

## Description

Book management application that uses Kotlin and Spring Boot 2 framework.

Libraries used : 
- Spring 5
- Jersey
- Hibernate (with JPA)
- H2
- JUnit (with kotlin-test)
- Mockito (with mockito-kotlin)

## Prerequisites

If you are not familiar with Kotlin syntax have a look to the official documentation : 
- https://kotlinlang.org/docs/reference/basic-syntax.html
- https://kotlinlang.org/docs/reference/idioms.html


## Run the app

Execute this command in a terminal:

```bash
./gradlew bootRun
```

Open `http://localhost:8080/books/` in your browser

## Model

```kotlin
@Entity
@Table(name = "book")
data class Book(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                var id: Long? = null,
                var title: String? = null,
                var publication: LocalDate? = null,
                @ManyToOne
                var author: Author? = null)

@Entity
@Table(name = "author")
data class Author(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                  var id: Long? = null,
                  var name: String? = null,
                  @OneToMany(mappedBy = "author")
                  var books: List<Book> = mutableListOf())
```

```kotlin
@Repository
interface IBookRepository : PagingAndSortingRepository<Book, Long>

@Repository
interface IAuthorRepository : PagingAndSortingRepository<Author, Long>
```

## Book management service
```kotlin
@Service
class BookService {
    @Autowired
    private lateinit var bookRepository: IBookRepository

    @Transactional(readOnly = true)
    fun getBook(@PathParam("id") id: Long): BookDto {
        return bookRepository.findById(id).map { BookDtoConverter.convert(it) }.orElse(null)
                ?: throw NotFoundException("Book $id does not exist")
    }

    // ...
}
```

```kotlin
data class BookDto(var id: Long? = null,
                var title: String? = null,
                var publication: LocalDate? = null,
                var authorId: Long? = null,
                var authorName: String? = null)
```


```kotlin
object BookDtoConverter : IDtoConverter<Book, BookDto> {
    override fun convert(entity: Book): BookDto =
        BookDto(id = entity.id,
                title = entity.title,
                publication = entity.publication,
                authorId = entity.author?.id,
                authorName = entity.author?.name ?: "")
}
```

## The REST layer

```kotlin
@Path("/book")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
class BookResource {
    @Autowired
    private lateinit var bookService: BookService

    @GET
    @Path("/{id}")
    fun getBook(@PathParam("id") id: Long) = bookService.getBook(id)

    // ...
}
```

```kotlin
@Component
class JerseyConfig() : ResourceConfig() {
    init {
        register(BookResource())
    }
}
```

## Tests with mockito-kotlin

```kotlin
@RunWith(SpringRunner::class)
class BookServiceTest {
    @Mock
    private lateinit var authorRepository: IAuthorRepository
    @Mock
    private lateinit var bookRepository: IBookRepository
    @InjectMocks
    private lateinit var bookService: BookService

    @Test
    fun testGetBook() {
        /* Given */
        val bookId = 1L
        whenever(bookRepository.findById(bookId)).thenReturn(Optional.of(Book(id = bookId)))

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

    // ...
}
```
