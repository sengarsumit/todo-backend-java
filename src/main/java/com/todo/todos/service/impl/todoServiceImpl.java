package com.todo.todos.service.impl;

import com.todo.todos.Repository.TodoRepository;
import com.todo.todos.entity.Todo;
import com.todo.todos.entity.User;
import com.todo.todos.model.TodoStatus;
import com.todo.todos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class todoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo createTodo(Todo todo, User user) {
        todo.setUser(user);
        todo.setStatus(TodoStatus.PENDING);
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(Long id, Todo updatedTodo, User user) {
        Todo existing =getTodayById(id,user).orElseThrow(()-> new RuntimeException("Todo not found"));
        existing.setTitle(updatedTodo.getTitle());
        existing.setDescription(updatedTodo.getDescription());
        existing.setDueDate(updatedTodo.getDueDate());
        return todoRepository.save(existing);
    }

    @Override
    public void deleteTodo(long id, User user) {
        Todo todo =getTodayById(id,user).orElseThrow(()-> new RuntimeException("Todo not found"));
        todoRepository.delete(todo);
    }

    @Override
    public Todo markAsCompleted(Long id, User user) {
        Todo todo =getTodayById(id,user).orElseThrow(()-> new RuntimeException("Todo not found"));
        todo.setStatus(TodoStatus.COMPLETED);
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> searchTodos(String title, User user) {
        return todoRepository.findByTitleContainingAndUser(title,user);
    }

    @Override
    public List<Todo> getUpcomingTodos(User user) {
        return todoRepository.findByDueDateAfterAndUser(LocalDate.now(),user);
    }

    @Override
    public Optional<Todo> getTodayById(Long id, User user) {
        return todoRepository.findByIdAndUser(id,user);
    }
}
