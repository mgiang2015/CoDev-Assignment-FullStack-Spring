package app.backend.structures;

import app.backend.model.Book;
import app.backend.model.PersonRepository;
import app.backend.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonRepository personRepository;

    @CrossOrigin
    @GetMapping("/allPeople")
    public List<Person> allPeople() {
        return personRepository.findAllPeople();
    }

    @CrossOrigin
    @GetMapping("/people")
    public Person getPersonById(@RequestParam(name="id", required = true)String id) {
        try {
            Person person = personRepository.findById(Integer.parseInt(id));
            if (person == null) {
                logger.error("No person with id {} exist", id);
                throw new Exception("No person with given id exists in database");
            }

            return person;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return null;
    }

}
