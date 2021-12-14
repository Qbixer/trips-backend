package com.kgp.trips.user.dto;

import com.kgp.trips.user.entity.User;
import com.kgp.trips.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {

    Integer id;
    String email;
    Set<String> roles;

    public static UserProfileDTO createUserProfileDTO(User user) {
        return UserProfileDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(UserRole::toString).collect(Collectors.toSet()))
                .build();
    }
}
