package com.example.pracktwo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank()
    @Column(unique = true, nullable = false)
    private String title;
    @NotBlank()
    @Column(nullable = false)
    private String description;
    @Column(name = "date_public", nullable = false)
    private LocalDate datePublic;

    public Book() {}

    public Book(String title, String description, LocalDate datePublic) {
        this.title = title;
        this.description = description;
        this.datePublic = datePublic;
    }

    public Book(long id, String title, String description, LocalDate datePublic) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.datePublic = datePublic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDatePublic() {
        return datePublic;
    }

    public void setDatePublic(LocalDate datePublic) {
        this.datePublic = datePublic;
    }
}
