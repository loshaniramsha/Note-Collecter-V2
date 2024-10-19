package com.example.Note_Collecter_V2.service.impl;
import com.example.Note_Collecter_V2.dao.UserDAO;
import com.example.Note_Collecter_V2.dto.impl.UserDTO;
import com.example.Note_Collecter_V2.entity.impl.UserEntity;
import com.example.Note_Collecter_V2.exception.UserNoteFoundException;
import com.example.Note_Collecter_V2.secure.JWTAuthhResponse;
import com.example.Note_Collecter_V2.secure.SignIn;
import com.example.Note_Collecter_V2.service.AuthService;
import com.example.Note_Collecter_V2.service.JWTService;
import com.example.Note_Collecter_V2.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {
    private final UserDAO userDAO;
    private final Mapping mapping;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JWTAuthhResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
       var user= userDAO.findAllByEmail(signIn.getEmail()).orElseThrow(()-> new UserNoteFoundException("User Not Found"));
      var generateToken= jwtService.generateToken(user);
      return JWTAuthhResponse.builder().token(generateToken).build();

    }

    @Override
    public JWTAuthhResponse signUp(UserDTO userDTO) {
        //Save User
        UserEntity savedUser=userDAO.save(mapping.toUserEntity(userDTO));
        //generate token
        var generateToken=jwtService.generateToken(savedUser);
        return JWTAuthhResponse.builder().token(generateToken).build();
    }

    @Override
    public JWTAuthhResponse refreshToken(String accessToken) {
        //extract user name
        var userName=jwtService.extractUserName(accessToken);
        //check the user availability
        var findUser=userDAO.findAllByEmail(userName).orElseThrow(()-> new UserNoteFoundException("User Not Found"));
        var refreshToken=jwtService.refreshToken(findUser);
        return JWTAuthhResponse.builder().token(refreshToken).build();

    }
}
