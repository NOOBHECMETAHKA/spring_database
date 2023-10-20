package com.example.pracktwo.repo;

import com.example.pracktwo.models.UserSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface UserSystemRepository extends ListCrudRepository<UserSystem, Long> {

}
