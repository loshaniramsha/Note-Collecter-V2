package com.example.Note_Collecter_V2.dto.impl;

import com.example.Note_Collecter_V2.dto.UserStates;
import com.example.Note_Collecter_V2.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserDTO implements UserStates {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profilepic;
    private Role role;
    private List<NoteDTO> notes;
}
