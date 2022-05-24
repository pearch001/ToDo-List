package com.todolist.todolistapi.api;

import com.todolist.todolistapi.model.JwtRequest;
import com.todolist.todolistapi.model.JwtResponse;
import com.todolist.todolistapi.model.entities.User;
import com.todolist.todolistapi.services.UserService;
import com.todolist.todolistapi.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
@Slf4j
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    @Autowired
    private UserService user_service;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @PostMapping(value="/signup")
    public ResponseEntity<User> signupUser(@RequestBody User user){
        //Creating URI that would be passed into the response entity .created method
        log.info("hey");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/signup/user").toUriString());

        return ResponseEntity.created(uri).body(user_service.saveUser(user));
    }

    @PostMapping(value="/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
            log.info("login");
        }catch (BadCredentialsException e){

            throw new Exception("Incorrect username or password",e);
        }
        final UserDetails userDetails = user_service.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

}


