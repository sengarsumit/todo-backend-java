package com.todo.todos.service.impl;

import com.todo.todos.Repository.UserRepository;
import com.todo.todos.entity.User;
import com.todo.todos.model.LoginRequest;
import com.todo.todos.model.RegisterRequest;
import com.todo.todos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User registerUser(RegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User does not exist"));
    }


    @Override
    public User login(LoginRequest request) {
        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("Invalid username and password"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }
}
