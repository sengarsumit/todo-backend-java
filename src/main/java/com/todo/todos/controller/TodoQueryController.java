package com.todo.todos.controller;


import com.todo.todos.entity.Todo;
import com.todo.todos.entity.User;
import com.todo.todos.service.TodoService;
import com.todo.todos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoQueryController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Todo>> searchTodos(@RequestParam String title, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok(todoService.searchTodos(title, user));
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Todo>> getUpcomingTodos(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return ResponseEntity.ok(todoService.getUpcomingTodos(user));
    }
}

