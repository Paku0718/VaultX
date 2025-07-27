package com.yourbank.bank_app.controller;

import com.yourbank.bank_app.entity.User;
import com.yourbank.bank_app.enums.Role;
import com.yourbank.bank_app.repository.UserRepository;
import com.yourbank.bank_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email, Authentication authentication) {
        String requesterEmail = authentication.getName();

        User requester = userRepository.findByEmail(requesterEmail)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        // ✅ Allow if requester is EMPLOYEE or MANAGER or ADMIN
        if (requester.getRole() == Role.EMPLOYEE || requester.getRole() == Role.MANAGER || requester.getRole() == Role.ADMIN) {
            return ResponseEntity.ok(userService.getUserByEmail(email));
        }

        // ✅ Allow if customer is accessing their own data
        if (requester.getRole() == Role.CUSTOMER && requesterEmail.equals(email)) {
            return ResponseEntity.ok(userService.getUserByEmail(email));
        }

        // ❌ Otherwise forbidden
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
