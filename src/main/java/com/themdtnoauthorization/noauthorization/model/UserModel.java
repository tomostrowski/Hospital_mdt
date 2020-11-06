package com.themdtnoauthorization.noauthorization.model;

import lombok.Data;

@Data
public class UserModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String name;
    private String username;
    private String role;
    private boolean isEnabled;
}
