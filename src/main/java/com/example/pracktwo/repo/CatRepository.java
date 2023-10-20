package com.example.pracktwo.repo;

import com.example.pracktwo.models.Cat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface CatRepository extends ListCrudRepository<Cat, Long> {

}
