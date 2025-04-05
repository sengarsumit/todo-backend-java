package com.todo.todos.controller;


import com.todo.todos.entity.Todo;
import com.todo.todos.entity.User;
import com.todo.todos.service.TodoService;
import com.todo.todos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo, Principal principal)
    {
        User user=userService.findByEmail(principal.getName());
        return ResponseEntity.ok(todoService.createTodo(todo,user));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok(todoService.updateTodo(id, updatedTodo, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        todoService.deleteTodo(id, user);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Todo> markAsCompleted(@PathVariable Long id, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok(todoService.markAsCompleted(id, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getById(@PathVariable Long id, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return todoService.getTodayById(id, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
