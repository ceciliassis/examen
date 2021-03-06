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


    @PostMapping("")
    public ResponseEntity <?> createCustomer(@RequestBody Customer customer) {

        if (customer.getEmail ().isEmpty ())
            return new ResponseEntity <> ( "MISSING INFO", HttpStatus.NOT_ACCEPTABLE );

        if (isRegistered ( customer.getEmail () ))
            return new ResponseEntity <> ( "CONFLICT", HttpStatus.CONFLICT );

        customerRepository.save ( customer );
        return new ResponseEntity <> ( "CUSTOMER " + customer.getId ().toString () + " CREATED", HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteCustomer(@PathVariable Long id) {

        if (!customerRepository.exists ( id )) {
            return new ResponseEntity <> ( "CUSTOMER " + id.toString () + " NOT FOUND", HttpStatus.NOT_FOUND );
        }

        customerRepository.delete ( id );
        return new ResponseEntity <> ( "CUSTOMER " + id.toString () + " DELETED", HttpStatus.OK );
    }

    @PatchMapping("/{id}")
    public ResponseEntity <?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {

        if (!customerRepository.exists ( id )) {
            return new ResponseEntity <> ( "CUSTOMER " + id.toString () + " NOT FOUND", HttpStatus.NOT_FOUND );
        }

        Customer currentCustomer = customerRepository.findOne ( id );
        if (!customer.getName ().isEmpty ()) {
            currentCustomer.setName ( customer.getName () );
        }
        if (!customer.getEmail ().isEmpty ()) {
            currentCustomer.setEmail ( customer.getEmail () );
        }

        customerRepository.save ( currentCustomer );
        return new ResponseEntity <> ( "CUSTOMER " + id.toString () + " UPDATED", HttpStatus.OK );
    }


    @GetMapping("")
    public Iterable <Customer> findAll() {
        return customerRepository.findAll ();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerRepository.findOne ( id );
    }


    private boolean isRegistered(String email) {
        Customer customer = customerRepository.findByEmail ( email );
        return customer != null;

    }

}
