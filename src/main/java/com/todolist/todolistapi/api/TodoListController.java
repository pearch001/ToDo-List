package com.todolist.todolistapi.api;

import com.todolist.todolistapi.model.entities.ToDoList;
import com.todolist.todolistapi.model.entities.User;
import com.todolist.todolistapi.model.requests.AddItem;
import com.todolist.todolistapi.services.ToDoListService;
import com.todolist.todolistapi.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequestMapping("/api/v1/todolist")
@RestController
public class TodoListController {
    @Autowired
    private ToDoListService toDoListService;

    @PostMapping(value="/add")
    public ResponseEntity<?> signupUser(@RequestBody AddItem addItem){
        //Creating URI that would be passed into the response entity .created method
        log.info("hey");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/todolist/add").toUriString());
        ToDoList toDoList = new ToDoList(addItem.getItem());
        toDoListService.saveItem(addItem.getEmail(), toDoList);
        return ResponseEntity.ok(" ");
    }
}
