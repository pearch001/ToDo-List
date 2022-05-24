package com.todolist.todolistapi.services;

import com.todolist.todolistapi.model.entities.ToDoList;

public interface ToDoListInt {
    void saveItem(String userEmail, ToDoList item);
}
