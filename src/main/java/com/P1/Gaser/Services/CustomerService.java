package com.P1.Gaser.Services;

import com.P1.Gaser.Entity.Customer;
import com.P1.Gaser.Entity.User;
import com.P1.Gaser.Exception.ResourceNotFoundException;
import com.P1.Gaser.Repositories.CustomerRepository;
import com.P1.Gaser.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final UserRepository userRepository;

    public CustomerService(
            CustomerRepository customerRepository,
            UserRepository userRepository) {

        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }


    public Customer addCustomer(Customer customer) {

        Long userId = customer.getUser().getUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId));

        customer.setUser(user);

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id: " + id));
    }

    public Customer updateCustomer(Long id, Customer customer) {

        Customer existingCustomer =
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer not found with id: " + id));

        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setAddress(customer.getAddress());

        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long id) {

        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Customer not found with id: " + id);
        }

        customerRepository.deleteById(id);
    }
}