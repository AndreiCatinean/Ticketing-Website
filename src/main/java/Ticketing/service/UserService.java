package com.example.Online.Ticketing.service;

import com.example.Online.Ticketing.model.User;
import com.example.Online.Ticketing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Registers a new user after encrypting their password.
    public void registerUser(User user) {

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    // Authenticates a user based on email and password.
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}