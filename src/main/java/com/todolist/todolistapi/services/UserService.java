package com.todolist.todolistapi.services;

import com.todolist.todolistapi.dao.ToDoListDao;
import com.todolist.todolistapi.dao.UserDao;
import com.todolist.todolistapi.model.JwtRequest;
import com.todolist.todolistapi.model.entities.ToDoList;
import com.todolist.todolistapi.model.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserInt, UserDetailsService {
    private final UserDao userDao;
    private final ToDoListDao toDoListDao;


    @Autowired
    private  PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;



    @Override
    public User saveUser(User user) {
        log.info("Saving new user");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }





    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userDao.findByEmail(email);
        if (user != null) {
            Collection<SimpleGrantedAuthority> claims = new ArrayList<>();
            if (email.equals("admin")){
            claims.add(new SimpleGrantedAuthority("Role_Admin"));
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    claims);
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }



    @Override
    public void login(JwtRequest request) throws  Exception{

        log.info(request.getEmail());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
