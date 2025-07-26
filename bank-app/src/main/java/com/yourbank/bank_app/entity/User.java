package com.yourbank.bank_app.entity;

import com.yourbank.bank_app.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;

    private String password;  // Encoded
    private String accountType;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true)
    private String accountNumber;

    private Double balance;
}
