package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.ShoppingCart;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    //    Method: GET
    //    Route: orders/customer/{customerId}
    //    Description: Returns all customer's shopping carts
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Iterable<ShoppingCart>> findAllByCustomer(@PathVariable Long customerId) {
        //  TODO - suelen: Validar se cliente ou não
        Customer customer = customerRepository.findOne(customerId);
        return new ResponseEntity<>(shoppingCartRepository.findByCustomer(customer), HttpStatus.OK);
    }

    // TODO - suelen
    //    Method: GET
    //    Route: customers/{customerId}/cart
    //    Description: Returns customer's current shopping cart
}
