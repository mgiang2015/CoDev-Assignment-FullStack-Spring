package app.backend.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "people")
public class People {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;
    Long country_id;

    // Many-to-Many mapping
    @ManyToMany(targetEntity = Books.class)
    private List books; // rented books

    public People() {
        id = -1;
        name = "";
        country_id = 0L;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public People(int id, String name, Long country_id) {
        this.id = id;
        this.name = name;
        this.country_id = country_id;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Event listeners
    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Setters and getters. Necessary for JPA

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

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public List getBooks() {
        return books;
    }

    public void setBooks(List books) {
        this.books = books;
    }
}
