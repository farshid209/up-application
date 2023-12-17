package org.example.controller;

import org.example.entity.Customer;
import org.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Customer readById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody Customer customer) {
        service.create(customer);
    }

    @PutMapping("/edit")
    public void edit(@RequestBody Customer customer) {
        service.update(customer);
    }

    @PostMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateById(id);
    }
}
