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
public class PersonRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @PersistenceContext
    private final EntityManager entityManager;

    public PersonRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Person> findAllPeople() {
        return entityManager.createQuery("SELECT * FROM people", Person.class).getResultList();
    }

    public Person findById(int id) {
        Person p = entityManager.find(Person.class, id);
        logger.info("Person -> {}", p);
        return p;
    }

    public void insert(Person p) {
        entityManager.persist(p);
        logger.info("Inserted person -> {}", p);
    }

    // Update and delete not necessary
}
