package com.example.pracktwo.repo;

import com.example.pracktwo.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface BookRepository extends ListCrudRepository<Book, Long> {
}
