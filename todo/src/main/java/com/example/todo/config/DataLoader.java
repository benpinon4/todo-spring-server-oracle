package com.example.todo.config;

import com.example.todo.models.TodoItem;
import com.example.todo.repository.ToDoItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ToDoItemRepository todoItemRepository;

    @Override
    public void run(String... args) throws Exception {
        List<TodoItem> todos = (List<TodoItem>) todoItemRepository.findAll();

        if (todos.size() == 0) {
            TodoItem todo1 = new TodoItem();
            TodoItem todo2 = new TodoItem();

            todo1.setDescription("this is the first todo");
            todo1.setCreatedDate(Instant.now());

            todo2.setDescription("This is the second todo");
            todo2.setCreatedDate(Instant.now());

            todoItemRepository.save(todo1);
            todoItemRepository.save(todo2);

        }
    }
}