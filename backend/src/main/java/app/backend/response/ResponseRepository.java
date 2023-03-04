package app.backend.response;

import app.backend.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public List<Response> getTop3ReadBooks(Long country_id) {
        List<Object[]> top3BooksWithAuthor = entityManager.createNativeQuery(
                "SELECT book_name, author_name FROM (\n" +
                        "\t(\n" +
                        "\t\tSELECT book_id AS book_info_id, book_name, name AS author_name FROM\n" +
                        "\t\t(\n" +
                        "\t\t\tSELECT book_id, book_name, author_id FROM\n" +
                        "\t\t\t\t(SELECT id, name AS book_name FROM books) book_id_name\n" +
                        "\t\t\t\tINNER JOIN author_books ON id = book_id\n" +
                        "\t\t) book_info INNER JOIN authors ON author_id = id\n" +
                        "\t) book_id_name_author\n" +
                        "\n" +
                        "\tINNER JOIN\n" +
                        "\n" +
                        "\t(\n" +
                        "\t\tSELECT book_id, COUNT(person_id) FROM (\n" +
                        "\t\t\tSELECT book_id, person_id FROM\n" +
                        "\t\t\t\tpeople INNER JOIN book_rents ON id = person_id\n" +
                        "\t\t\tWHERE country_id = " + country_id + "\n" +
                        "\t\t) filtered_book GROUP BY book_id ORDER BY count DESC LIMIT 3\n" +
                        "\t) book_id_count\n" +
                        "\n" +
                        "\tON book_info_id = book_id\n" +
                        ")"
        ).getResultList();

        logger.info("top3BooksWithAuthor = " + top3BooksWithAuthor);

        List<String> top3Borrowers = entityManager.createNativeQuery(
                "SELECT name FROM\n" +
                        "\tpeople\n" +
                        "\n" +
                        "\tLEFT OUTER JOIN\n" +
                        "\n" +
                        "\t(SELECT person_id, COUNT(book_id) FROM book_rents GROUP BY person_id ORDER BY count DESC LIMIT 3) rent_count\n" +
                        "\n" +
                        "\tON id = person_id\n" +
                        "\n" +
                        "WHERE country_id = " + country_id
        ).getResultList();

        logger.info("top3Borrowers = " + top3Borrowers);

        ArrayList<Response> result = new ArrayList<>();

        for (Object[] obj : top3BooksWithAuthor) {
            Response res = new Response();
            res.setAuthorName(String.valueOf(obj[0]));
            res.setBookName(String.valueOf(obj[1]));
            res.setBorrower(top3Borrowers);
            result.add(res);
        }

        return result;
    }
}

/* Query for top books
SELECT book_name, author_name FROM (
	(
		SELECT book_id AS book_info_id, book_name, name AS author_name FROM
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
SELECT name FROM
	people

	LEFT OUTER JOIN

	(SELECT person_id, COUNT(*) FROM book_rents GROUP BY person_id ORDER BY count DESC LIMIT 3) rent_count

	ON id = person_id

WHERE country_id = 0;
 */

