package com.booleanuk.api.cinema.customer;

import com.booleanuk.api.cinema.response.DataResponse;
import com.booleanuk.api.cinema.response.ListResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customers")
public class CustomerController {
    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ListResponse<Customer> getAll() {
        return new ListResponse<>(this.repository.findAll());
    }

    @GetMapping("{id}")
    public DataResponse<Customer> getById(@PathVariable("id") Integer id) {
        Customer found = this.repository.findById(id).orElseThrow();
        return new DataResponse<>(found);
    }

    record PostCustomer(String name, String email, String phone) {}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DataResponse<Customer> create(@RequestBody PostCustomer request) {
        Customer customer = new Customer(request.name(), request.email(), request.phone());
        Customer saved = this.repository.save(customer);
        return new DataResponse<>(saved);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("{id}")
    public DataResponse<Customer> update(@RequestBody PostCustomer request, @PathVariable("id") Integer id) {
        Customer customer = this.repository.findById(id).orElseThrow();
        customer.setPhone(request.phone() != null ? request.phone() : customer.getPhone());
        customer.setEmail(request.email() != null ? request.email() : customer.getEmail());
        customer.setName(request.name() != null ? request.name() : customer.getName());

        Customer saved = this.repository.save(customer);

        return new DataResponse<>(saved);
    }

    @DeleteMapping("{id}")
    public DataResponse<Customer> delete(@PathVariable("id") Integer id) {
        Customer found = this.repository.findById(id).orElseThrow();
        this.repository.deleteById(id);
        return new DataResponse<>(found);
    }
}
