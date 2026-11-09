
package com.example.todo_app;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {

    private List<Todo> todos = new ArrayList<>();
    private int nextId = 1;

    // Add a new task
    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        todo.setId(nextId++);
        todos.add(todo);
        return todo;
    }

    // View all tasks
    @GetMapping
    public List<Todo> getTodos() {
        return todos;
    }

    // Update a task
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable int id,
                           @RequestBody Todo updatedTodo) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todo.setTitle(updatedTodo.getTitle());
                todo.setCompleted(updatedTodo.isCompleted());
                return todo;
            }
        }
        return null;
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable int id) {
        todos.removeIf(todo -> todo.getId() == id);
        return "Todo deleted successfully";
    }
}
