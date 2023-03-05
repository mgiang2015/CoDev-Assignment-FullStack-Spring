package app.backend.response;

import app.backend.model.Author;

import java.util.List;

public class Top3BookResponse {
    private String name;    // book name
    private Author author;  // author name

    private List borrower;

    public Top3BookResponse() {
        name = "";
        author = null;
        borrower = null;
    }

    public Top3BookResponse(String name, Author author, List borrower) {
        this.name = name;
        this.author = author;
        this.borrower = borrower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List getBorrower() {
        return borrower;
    }

    public void setBorrower(List borrower) {
        this.borrower = borrower;
    }
}
