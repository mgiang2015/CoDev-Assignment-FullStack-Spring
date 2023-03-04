package app.backend.response;

import javax.persistence.*;
import java.util.List;

public class Response {
    private String bookName;    // book name
    private String authorName;  // author name

    private List borrower;

    public Response() {
        bookName = "";
        authorName = "";
        borrower = null;
    }

    public Response(String bookName, String authorName, List borrower) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.borrower = borrower;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<String> getBorrower() {
        return borrower;
    }

    public void setBorrower(List<String> borrower) {
        this.borrower = borrower;
    }
}
