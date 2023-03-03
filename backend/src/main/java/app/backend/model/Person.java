package app.backend.model;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "createdAt")
    private OffsetDateTime createdAt;
    @Column(name = "updatedAt")
    private OffsetDateTime updatedAt;
    @Column(name = "country_id")
    Long country_id;

    // Many-to-Many mapping
    @ManyToMany(targetEntity = Book.class)
    @JoinTable(name = "book_rents")
    private List books; // rented books

    public Person() {
        name = "";
        country_id = 0L;
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
    }

    public Person(String name, Long country_id) {
        this.name = name;
        this.country_id = country_id;
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
    }

    // Event listeners
    @PrePersist
    public void onCreate() {
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = OffsetDateTime.now();
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", country_id=" + country_id +
                ", books=" + books +
                '}';
    }

}
