package com.kgp.trips.user.service;

import com.kgp.trips.user.dto.UserCredentialsDTO;
import com.kgp.trips.user.entity.User;
import com.kgp.trips.user.enums.UserRole;
import com.kgp.trips.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    public class UserAlreadyExistException extends Exception {
        UserAlreadyExistException(String message) {
            super(message);
        }
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public void register(UserCredentialsDTO userCredentialsDTO) throws UserAlreadyExistException {
        if(checkIfUserExist(userCredentialsDTO.getEmail())){
            throw new UserAlreadyExistException("User already exists for this email");
        }
        User user = new User();
        user.setEmail(userCredentialsDTO.getEmail());
        Set<UserRole> roles = new HashSet<>();
        roles.add(UserRole.USER);
        user.setRoles(roles);
        encodePassword(user, userCredentialsDTO);
        userRepository.save(user);
    }

    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private void encodePassword( User user, UserCredentialsDTO userCredentialsDTO){
        user.setPassword(passwordEncoder.encode(userCredentialsDTO.getPassword()));
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
