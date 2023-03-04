package app.backend.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PersonRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonRepository personRepository;

    @Test
    public void findById() {
        Person p = personRepository.findById(2);
        assertNotNull("Person does not exist", p);
        assertEquals("Expected person with id 1", 2, p.getId());
    }

    @Test
    @Transactional
    public void insert() {
        Person p = new Person("Walter White", 84L, null);
        personRepository.insert(p);
        assertNotNull("ID is null", p.getId());
    }
}
