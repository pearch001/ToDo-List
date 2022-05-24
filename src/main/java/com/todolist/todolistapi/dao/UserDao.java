package com.todolist.todolistapi.dao;

import com.todolist.todolistapi.model.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserDao extends CrudRepository<User, Long> {
    Optional  <User> findById(Long Id);
    @Query(value = "SELECT * FROM job_seeker WHERE email = ?1", nativeQuery = true)
    User findByEmail (String email);
}
