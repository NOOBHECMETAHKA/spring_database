package com.example.pracktwo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.UUID;

@Entity
public class Cat {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Getter
    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;
    @Min(0)
    @Column(name = "count_likes")
    private long like = 0;
    @Min(0)
    @Column(name = "count_views")
    private long view = 0;

    public Cat() {}

    public Cat(String name) {
        this.name = name;
    }

    public Cat(String name, Long like, Long view) {
        this.name = name;
        this.like = like;
        this.view = view;
    }

    public Cat(long id, String name, long like, long view) {
        this.id = id;
        this.name = name;
        this.like = like;
        this.view = view;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(long like) {
        this.like = like;
    }

    public Long getView() {
        return view;
    }

    public void setView(Long view) {
        this.view = view;
    }
}
