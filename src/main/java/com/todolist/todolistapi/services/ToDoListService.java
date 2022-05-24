package com.todolist.todolistapi.services;

import com.todolist.todolistapi.dao.ToDoListDao;
import com.todolist.todolistapi.dao.UserDao;
import com.todolist.todolistapi.model.entities.ToDoList;
import com.todolist.todolistapi.model.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ToDoListService implements ToDoListInt{
    private final UserDao userDao;
    private final ToDoListDao toDoListDao;
    @Override
    public void saveItem(String userEmail, ToDoList item) {
        User user = userDao.findByEmail(userEmail);
        item = toDoListDao.save(item);
        if (user != null) {
            user.getToDoLists().add(item);
        } else {
            throw new UsernameNotFoundException("User not found with email: " + userEmail);
        }
    }
}
