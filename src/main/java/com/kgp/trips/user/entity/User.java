package com.kgp.trips.user.entity;


import com.kgp.trips.user.enums.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "my_user")
public class User {

    @Id
    @GeneratedValue(generator = "my_user_id_seq")
    Integer id;

    String email;
    String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass= UserRole.class)
    @CollectionTable(name="user_role")
    @Column(name = "role")
    Set<UserRole> roles;


}
