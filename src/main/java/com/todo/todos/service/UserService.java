package com.todo.todos.service;

import com.todo.todos.entity.User;
import com.todo.todos.model.LoginRequest;
import com.todo.todos.model.RegisterRequest;

public interface UserService {
    User registerUser(RegisterRequest request);
    User findByEmail(String email);
    User login(LoginRequest request);
}
