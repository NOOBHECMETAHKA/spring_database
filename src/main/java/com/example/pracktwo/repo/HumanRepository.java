package com.example.pracktwo.repo;

import com.example.pracktwo.models.Human;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface HumanRepository extends ListCrudRepository<Human, Long> {

}
