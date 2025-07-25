package com.yourbank.bank_app.entity;

import com.yourbank.bank_app.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;
    private String password;  // will be encoded
    private String accountType;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true)
    private String accountNumber;

    private Double balance;
}
