package com.example.todo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.models.TodoItem;

@Repository
public interface ToDoItemRepository extends CrudRepository<TodoItem, Long>{
    
}
