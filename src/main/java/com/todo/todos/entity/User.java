package com.todo.todos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Email(message ="Provide a valid email")
    @Column(unique=true, nullable=false)
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Column(nullable=false)
    private String password;

    private String name;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos;

}
