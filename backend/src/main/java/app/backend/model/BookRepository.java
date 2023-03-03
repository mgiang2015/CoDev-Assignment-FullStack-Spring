package app.backend.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @PersistenceContext
    private final EntityManager entityManager;

    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Book> findAllBooks() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    public Book findById(int id) {
        Book b = entityManager.find(Book.class, id);
        logger.info("Book -> {}", b);
        return b;
    }

    public void insert(Book b) {
        entityManager.persist(b);
        logger.info("Inserted book -> {}", b);
    }
}
