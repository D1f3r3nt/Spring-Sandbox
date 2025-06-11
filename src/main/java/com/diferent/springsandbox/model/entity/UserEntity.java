package com.diferent.springsandbox.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users", schema = "spring_sandbox")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;
}
