package com.example.demo.repository;

import com.example.demo.data.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, String> {
}
