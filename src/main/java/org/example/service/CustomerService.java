package org.example.service;

import org.example.entity.Customer;
import org.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer getById(Long id) {
        Customer customer = repository.findById(id).get();
        return customer;
    }

    public void create(Customer customer){
        customer.setActive(true);
        repository.save(customer);
    }

    public void update(Customer customer){
        Customer customer1=getById(customer.getId());

        customer1.setFirstName(customer.getFirstName());
        customer1.setLastName(customer.getLastName());
        customer1.setMobileNumber(customer.getMobileNumber());
        customer1.setAddress(customer.getAddress());
        repository.save(customer1);
    }

    public void deactivateById(Long id){
        Customer customer = getById(id);
        customer.setActive(false);
        repository.save(customer);
    }
}
