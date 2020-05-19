package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        if (!customerRepository.exists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(customerRepository.findOne(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Customer>> findAll() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        if (customer.getEmail().isEmpty() || isRegistered(customer.getEmail()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        customerRepository.save(customer);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        if (!customerRepository.exists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Customer customer = customerRepository.findOne(id);
        customerRepository.delete(id);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        if (!customerRepository.exists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Customer currentCustomer = customerRepository.findOne(id);

        if (!customer.getName().isEmpty())
            currentCustomer.setName(customer.getName());

        if (!customer.getEmail().isEmpty())
            currentCustomer.setEmail(customer.getEmail());

        customerRepository.save(currentCustomer);

        return new ResponseEntity<>(currentCustomer, HttpStatus.OK);
    }

    private boolean isRegistered(String email) {
        Customer customer = customerRepository.findByEmail(email);
        return customer != null;
    }
}
