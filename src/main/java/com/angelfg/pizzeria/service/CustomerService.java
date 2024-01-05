package com.angelfg.pizzeria.service;

import com.angelfg.pizzeria.persistence.entity.CustomerEntity;
import com.angelfg.pizzeria.persistence.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerEntity findByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }

}
