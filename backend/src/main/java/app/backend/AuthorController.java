package app.backend;

import app.backend.model.Author;
import app.backend.model.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthorRepository authorRepository;

    @CrossOrigin
    @GetMapping("/allAuthors")
    public List<Author> allAuthors() {
        return authorRepository.findAllAuthors();
    }

    @CrossOrigin
    @GetMapping("/authors")
    public Author getAuthorById(@RequestParam(name="id", required = true) String id) {
        try {
            Author author = authorRepository.findById(Integer.parseInt(id));
            if (author == null) {
                logger.error("No author with id {} exist", id);
                throw new Exception("No author with given id exists");
            }

            return author;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return null;
    }
}
