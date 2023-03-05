package app.backend.response;

import app.backend.model.Author;
import app.backend.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
public class ResponseRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @PersistenceContext
    private final EntityManager entityManager;

    public ResponseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Top3BookResponse> getTop3ReadBooks(Long country_id) {
        List<Object[]> top3BooksWithAuthor = entityManager.createNativeQuery(
                "SELECT book_name, author_id, author_name, \"createdAt\", \"updatedAt\" FROM (\n" +
                        "        (\n" +
                        "                SELECT book_id AS book_info_id, book_name, name AS author_name, id as author_id, \"createdAt\", \"updatedAt\" FROM\n" +
                        "                (\n" +
                        "                        SELECT book_id, book_name, author_id FROM\n" +
                        "                                (SELECT id, name AS book_name FROM books) book_id_name\n" +
                        "                                INNER JOIN author_books ON id = book_id\n" +
                        "                ) book_info INNER JOIN authors ON author_id = id\n" +
                        "        ) book_id_name_author\n" +
                        "\n" +
                        "        INNER JOIN\n" +
                        "\n" +
                        "        (\n" +
                        "                SELECT book_id, COUNT(*) FROM (\n" +
                        "                        SELECT book_id, person_id FROM\n" +
                        "                                people INNER JOIN book_rents ON id = person_id\n" +
                        "                        WHERE country_id = " + country_id + "\n" +
                        "                ) filtered_book GROUP BY book_id ORDER BY count DESC LIMIT 3\n" +
                        "        ) book_id_count\n" +
                        "\n" +
                        "        ON book_info_id = book_id\n" +
                        ")"
        ).getResultList();

        logger.info("top3BooksWithAuthor = " + Arrays.deepToString(top3BooksWithAuthor.toArray()));

        List<Object[]> top3BorrowersString = entityManager.createNativeQuery(
                "SELECT person_id, name, \"createdAt\", \"updatedAt\" FROM\n" +
                        "\tpeople\n" +
                        "\n" +
                        "\tINNER JOIN\n" +
                        "\n" +
                        "\t(SELECT person_id, COUNT(*) FROM book_rents GROUP BY person_id) rent_count\n" +
                        "\n" +
                        "\tON id = person_id\n" +
                        "\n" +
                        "WHERE country_id = " + country_id + " ORDER BY count DESC LIMIT 3;"
        ).getResultList();

        logger.info("top3Borrowers = " + Arrays.deepToString(top3BorrowersString.toArray()));

        ArrayList<Person> top3Borrowers = new ArrayList<>();
        for (Object[] info : top3BorrowersString) {
            Person p = new Person();
            p.setId(Integer.parseInt(String.valueOf(info[0])));
            p.setName(String.valueOf(info[1]));
            // p.setCreatedAt(OffsetDateTime.parse(String.valueOf(info[2]), DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss.SSSX" )));
            // p.setUpdatedAt(OffsetDateTime.parse(String.valueOf(info[3]), DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss.SSSX" )));
            p.setCountry_id(country_id);
            top3Borrowers.add(p);
        }

        ArrayList<Top3BookResponse> result = new ArrayList<>();

        for (Object[] obj : top3BooksWithAuthor) {
            Author author = new Author();
            author.setId(Integer.parseInt(String.valueOf(obj[1])));
            author.setName(String.valueOf(obj[2]));
            // author.setCreatedAt(OffsetDateTime.parse(String.valueOf(obj[3]), DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss.SSSX" )));
            // author.setUpdatedAt(OffsetDateTime.parse(String.valueOf(obj[4]), DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss.SSSX" )));

            Top3BookResponse res = new Top3BookResponse();
            res.setAuthor(author);
            res.setName(String.valueOf(obj[0]));
            res.setBorrower(top3Borrowers);
            result.add(res);
        }

        return result;
    }
}

/* Query for top books
SELECT book_name, author_id, author_name, "createdAt", "updatedAt" FROM (
        (
                SELECT book_id AS book_info_id, book_name, name AS author_name, id as author_id, "createdAt", "updatedAt" FROM
                (
                        SELECT book_id, book_name, author_id FROM
                                (SELECT id, name AS book_name FROM books) book_id_name
                                INNER JOIN author_books ON id = book_id
                ) book_info INNER JOIN authors ON author_id = id
        ) book_id_name_author

        INNER JOIN

        (
                SELECT book_id, COUNT(*) FROM (
                        SELECT book_id, person_id FROM
                                people INNER JOIN book_rents ON id = person_id
                        WHERE country_id = 0
                ) filtered_book GROUP BY book_id ORDER BY count DESC LIMIT 3
        ) book_id_count

        ON book_info_id = book_id
);
*/

/* Query for top 3 borrowers
SELECT person_id, name, "createdAt", "updatedAt" FROM
	people

	INNER JOIN

	(SELECT person_id, COUNT(*) FROM book_rents GROUP BY person_id) rent_count

	ON id = person_id

WHERE country_id = 0 ORDER BY count DESC LIMIT 3;
 */

