package com.todolist.todolistapi.dao;

import com.todolist.todolistapi.model.entities.ToDoList;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ToDoListDao extends CrudRepository<ToDoList,Long> {
    Optional<ToDoList> findById(Long id);
}
