package com.example.Note_Collecter_V2.service;



import com.example.Note_Collecter_V2.dto.UserStates;
import com.example.Note_Collecter_V2.dto.impl.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    List<UserDTO> getAllUser();
    UserStates getUser(String userId);
    void deleteUser(String userId);
    void updatUser(String userId, UserDTO userDTO);
}
