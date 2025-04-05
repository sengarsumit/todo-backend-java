package com.todo.todos.entity;
import com.todo.todos.entity.User;
import com.todo.todos.model.TodoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String description;
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private TodoStatus status=TodoStatus.PENDING;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

}
