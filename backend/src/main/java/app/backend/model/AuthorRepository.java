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
public class AuthorRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @PersistenceContext
    private final EntityManager entityManager;

    public AuthorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Author> findAllAuthors() {
        return entityManager.createQuery("SELECT b FROM Author b", Author.class).getResultList();
    }

    public Author findById(int id) {
        Author b = entityManager.find(Author.class, id);
        logger.info("Author -> {}", b);
        return b;
    }

    public void insert(Author b) {
        entityManager.persist(b);
        logger.info("Inserted Author -> {}", b);
    }
}
