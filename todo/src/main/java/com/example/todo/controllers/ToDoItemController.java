package com.example.todo.controllers;

import com.example.todo.models.TodoItem;
import com.example.todo.repository.ToDoItemRepository;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/todoitems")
public class ToDoItemController {

    private final ToDoItemRepository todoItemRepository;

    public ToDoItemController(ToDoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @GetMapping("")
    public List<TodoItem> findAllTodoItems() {
        return (List<TodoItem>) todoItemRepository.findAll();
    }

    @PostMapping("")
    public TodoItem createTodoItem(@RequestBody TodoItem todoItem) {

        TodoItem newItem = new TodoItem();
        newItem.setDescription(todoItem.getDescription());
        newItem.setComplete(todoItem.isComplete());
        newItem.setCreatedDate(Instant.now());
        newItem.setModifiedDate(Instant.now());
        newItem = todoItemRepository.save(newItem);
        return newItem;
    }

    @GetMapping("/{id}")
    public Optional<TodoItem> getTodoItem(@PathVariable("id") long id) {
        return todoItemRepository.findById(id);
    }

    @PutMapping("/{id}")
    public TodoItem updateTodoItem(@PathVariable("id") long id, @RequestBody TodoItem todoItem) {

        Optional<TodoItem> item = todoItemRepository.findById(id);
        TodoItem updatedItem = new TodoItem();
        if (item.isPresent()) {
            updatedItem = item.get();
            updatedItem.setDescription(todoItem.getDescription());
            updatedItem.setComplete(todoItem.isComplete());
            updatedItem.setModifiedDate(Instant.now());
            updatedItem = todoItemRepository.save(updatedItem);
        }

        return updatedItem;
    }

    @DeleteMapping("/{id}")
    public void deleteTodoItem(@PathVariable("id") long id) {
        // todoItemRepository.deleteById(id);
        Optional<TodoItem> item = todoItemRepository.findById(id);
        if (item.isPresent()){
            todoItemRepository.delete(item.get());
        }
    }

}