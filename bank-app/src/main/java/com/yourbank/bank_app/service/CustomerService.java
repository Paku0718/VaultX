package com.yourbank.bank_app.service;

import com.yourbank.bank_app.entity.Customer;
import com.yourbank.bank_app.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepo;

    public Customer createCustomer(Customer customer) {
        customer.setAccountNumber(generateAccountNumber());
        customer.setBalance(0.0);
        return customerRepo.save(customer);
    }

    private String generateAccountNumber() {
        return "AC" + System.currentTimeMillis();
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepo.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Customer not found")
        );
    }
}
