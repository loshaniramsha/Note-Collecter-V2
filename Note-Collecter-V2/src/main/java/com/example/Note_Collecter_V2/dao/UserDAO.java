package com.example.Note_Collecter_V2.dao;


import com.example.Note_Collecter_V2.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserDAO extends JpaRepository<UserEntity, String> {

}
