package com.example.Note_Collecter_V2.service;

import com.example.Note_Collecter_V2.dto.impl.UserDTO;
import com.example.Note_Collecter_V2.secure.JWTAuthhResponse;
import com.example.Note_Collecter_V2.secure.SignIn;

public interface AuthService {
    JWTAuthhResponse signIn(SignIn signIn);
    JWTAuthhResponse signUp(UserDTO userDTO);
    JWTAuthhResponse refreshToken(String accessToken);
}
