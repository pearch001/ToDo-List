package com.todolist.todolistapi.model.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data

public class AddItem {
    @NotEmpty
    private String email;
    @NotEmpty
    private String item;
}
