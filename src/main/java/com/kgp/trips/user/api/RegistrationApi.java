package com.kgp.trips.user.api;

import com.kgp.trips.user.dto.UserCredentialsDTO;
import com.kgp.trips.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/register")
@RestController
public class RegistrationApi {

    @Autowired
    private UserService userService;

    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;
    @PostMapping("")
    public ResponseEntity userRegistration(@RequestBody @Valid UserCredentialsDTO userCredentialsDTO) {
        try {
            userService.register(userCredentialsDTO);
            return ResponseEntity.ok(ResponseEntity.EMPTY);
        } catch (UserService.UserAlreadyExistException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("User already exist");
        }
    }
}
