package app.backend;

import app.backend.model.Book;
import app.backend.model.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookRepository bookRepository;

    @CrossOrigin
    @GetMapping("/allBooks")
    public List<Book> allBooks() {
        return bookRepository.findAllBooks();
    }

    @CrossOrigin
    @GetMapping("/books")
    public Book getBookById(@RequestParam(name="id", required = true) String id) {
        try {
            Book book = bookRepository.findById(Integer.parseInt(id));
            if (book == null) {
                logger.error("No book with id {} exist", id);
                throw new Exception("No book with given id exists");
            }

            return book;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return null;
    }
}
