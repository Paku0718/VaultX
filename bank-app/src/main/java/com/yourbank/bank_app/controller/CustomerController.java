package com.yourbank.bank_app.controller;

import com.yourbank.bank_app.entity.Customer;
import com.yourbank.bank_app.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // ✅ Registration is typically open to unauthenticated users
    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    // ✅ Get customer by email — should be protected
    @GetMapping("/{email}")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER') or #email == authentication.name")  // own data or employee/manager
    public ResponseEntity<Customer> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }
}
