package com.yourbank.bank_app.controller;

import com.yourbank.bank_app.entity.User;
import com.yourbank.bank_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ✅ Registration: typically done unauthenticated
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    // ✅ Only manager/employee can fetch others, or user can see own info
    @GetMapping("/{email}")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER') or #email == authentication.name")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
}
