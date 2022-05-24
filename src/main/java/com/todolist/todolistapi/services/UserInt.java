package com.todolist.todolistapi.services;

import com.todolist.todolistapi.model.JwtRequest;
import com.todolist.todolistapi.model.entities.ToDoList;
import com.todolist.todolistapi.model.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserInt {

    User saveUser(User user);

    UserDetails loadUserByUsername(String username);
    void login(JwtRequest request) throws Exception;


}
