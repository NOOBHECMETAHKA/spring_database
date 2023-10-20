package com.example.pracktwo.repo;

import com.example.pracktwo.models.Pencil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface PencilRepository extends ListCrudRepository<Pencil, Long> {

}
