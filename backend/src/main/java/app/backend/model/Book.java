package app.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "\"createdAt\"")
    private OffsetDateTime createdAt;
    @Column(name = "\"updatedAt\"")
    private OffsetDateTime updatedAt;

    // Many-to-Many with people
    @ManyToMany(targetEntity = Person.class)
    @JoinTable(name = "book_rents", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    @JsonManagedReference
    private List people;

    public Book() {
        this.id = -1;
        this.name = null;
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
        people = null;
    }

    public Book(int id, String name, List people) {
        this.id = id;
        this.name = name;
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
        this.people = people;
    }

    @PrePersist
    public void onCreate() {
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }

    // setters and getters. JPA needs

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

    public List getPeople() {
        return people;
    }

    public void setPeople(List people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
