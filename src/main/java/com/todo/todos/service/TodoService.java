package com.todo.todos.service;

import com.todo.todos.entity.Todo;
import com.todo.todos.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface TodoService {
    Todo createTodo(Todo todo, User user);
    Todo updateTodo(Long id,Todo updatedTodo,User user);
    void deleteTodo(long id,User user);
    Todo markAsCompleted(Long id,User user);
    List<Todo> searchTodos(String title,User user);
    List<Todo> getUpcomingTodos(User user);
    Optional<Todo> getTodayById(Long id,User user);
}
