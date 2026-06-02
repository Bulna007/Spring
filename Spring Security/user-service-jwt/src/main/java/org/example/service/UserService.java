package org.example.service;

import org.example.entity.RegisterUserRequest;
import org.example.entity.RegisterUserResponse;
import org.example.entity.Users;
import org.example.repository.UserDetailsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest){
        //check if user is already present
        if(userDetailsRepository.findByUsername(registerUserRequest.getUsername()).isPresent()){
            throw new RuntimeException("User Already Exist");
        }

        Users users = new Users();
        users.setUsername(registerUserRequest.getUsername());
        //encode password
        users.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        users.setRole(registerUserRequest.getRole());
        //save user
        Users savedUser = userDetailsRepository.save(users);
        return new RegisterUserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getRole().name());
    }
}
