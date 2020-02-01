package com.vntofu.fastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vntofu.fastfood.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
