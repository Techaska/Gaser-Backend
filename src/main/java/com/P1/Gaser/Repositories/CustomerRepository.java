package com.P1.Gaser.Repositories;

import com.P1.Gaser.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}