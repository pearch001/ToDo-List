package com.todolist.todolistapi.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="todo_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp= "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message= "Kindly provide a valid email address")
    @Column(unique = true)
    private String email;
    private  String password;
    private String firstName;
    private String lastName;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<ToDoList> toDoLists = new ArrayList<>();

    public User( String email, String username, String password) {
        this.email = email;
        this.password = password;
    }

    @CreationTimestamp
    private Date dateCreated;
    @UpdateTimestamp
    private Date dateModified;


}



