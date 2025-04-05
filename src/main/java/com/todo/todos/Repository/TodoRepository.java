package com.todo.todos.Repository;

import com.todo.todos.entity.Todo;
import com.todo.todos.entity.User;
import com.todo.todos.model.TodoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findByTitleContainingAndUser(String title, User user);
    List<Todo> findByStatusAndUser(TodoStatus status,User user);
    List<Todo> findByDueDateAndUser(LocalDate date,User user);
    Optional<Todo> findByIdAndUser(Long id,User user);

    List<Todo> findByDueDateAfterAndUser(LocalDate dueDateAfter, User user);
}
