package app.backend.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "authors")
public class Authors {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    // One-to-Many to books - 1 author writes many books
    @OneToMany(targetEntity = Books.class)
    private List books;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // setters and getters for JPA

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List getBooks() {
        return books;
    }

    public void setBooks(List books) {
        this.books = books;
    }
}
