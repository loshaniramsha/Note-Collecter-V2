package com.example.Note_Collecter_V2.service.impl;

import com.example.Note_Collecter_V2.customeStatesCode.SelectedUserAndNoteErrorStatus;
import com.example.Note_Collecter_V2.dao.UserDAO;
import com.example.Note_Collecter_V2.dto.UserStates;
import com.example.Note_Collecter_V2.dto.impl.UserDTO;
import com.example.Note_Collecter_V2.entity.impl.UserEntity;
import com.example.Note_Collecter_V2.exception.DataPersistsExseption;
import com.example.Note_Collecter_V2.exception.UserNoteFoundException;
import com.example.Note_Collecter_V2.service.UserService;
import com.example.Note_Collecter_V2.util.Mapping;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity savedUser =
                userDAO.save(mapping.toUserEntity(userDTO));
        if (savedUser == null) {
            throw new DataPersistsExseption("User not saved");
        }
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<UserEntity> allUsers=userDAO.findAll();
        return mapping.asUserDTOList(allUsers);
    }

    @Override
    public UserStates getUser(String userId) {
        if(userDAO.existsById(userId)){
            UserEntity selectedUser = userDAO.getReferenceById(userId);
            return mapping.toUserDTO(selectedUser);
        }else {
            return new SelectedUserAndNoteErrorStatus(2, "User with id " + userId + " not found");
        }
    }



    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> existedUser = userDAO.findById(userId);
        if(!existedUser.isPresent()){
            throw new UserNoteFoundException("User with id " + userId + " not found");
        }else {
            userDAO.deleteById(userId);
        }

    }

    @Override
    public void updatUser(String userId, UserDTO userDTO) {
        Optional<UserEntity> tmpUser=userDAO.findById(userId);
        if (tmpUser.isPresent()){
            tmpUser.get().setFirstName(userDTO.getFirstName());
            tmpUser.get().setLastName(userDTO.getLastName());
            tmpUser.get().setEmail(userDTO.getEmail());
            tmpUser.get().setPassword(userDTO.getPassword());
            tmpUser.get().setProfilepic(userDTO.getProfilepic());
            userDAO.save(tmpUser.get());
        }
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userDAO.findAllByEmail(username)
                .orElseThrow(()-> new UserNoteFoundException("User Not Found"));
    }
}


