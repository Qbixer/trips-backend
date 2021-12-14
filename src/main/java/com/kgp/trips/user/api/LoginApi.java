package com.kgp.trips.user.api;

import com.kgp.trips.user.dto.UserCredentialsDTO;
import com.kgp.trips.user.dto.UserProfileDTO;
import com.kgp.trips.user.entity.User;
import com.kgp.trips.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RequestMapping("/login")
@RestController
public class LoginApi {

    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    UserService userService;

    @PostMapping("")
    ResponseEntity login(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        Authentication authentication = daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userCredentialsDTO.getEmail(), userCredentialsDTO.getPassword()));
        boolean isAuthenticated = isAuthenticated(authentication);
        if (isAuthenticated) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Optional<User> userByEmail = userService.getUserByEmail(userCredentialsDTO.getEmail());
            if(userByEmail.isPresent()) {
                return ResponseEntity.ok(UserProfileDTO.createUserProfileDTO(userByEmail.get()));
            }
        }
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    private boolean isAuthenticated(Authentication authentication) {
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}
