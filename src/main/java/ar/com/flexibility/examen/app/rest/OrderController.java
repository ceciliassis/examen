package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.ShoppingCart;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Iterable<ShoppingCart>> findAllByCustomer(@PathVariable Long customerId) {
        if (!customerRepository.exists(customerId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Customer customer = customerRepository.findOne(customerId);
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findByCustomer(customer);

        if (shoppingCarts.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    @GetMapping("customer/{customerId}/active")
    public ResponseEntity<ShoppingCart> getActiveShoppingCart(@PathVariable Long customerId) {
        if (!customerRepository.exists(customerId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Customer customer = customerRepository.findOne(customerId);
        ShoppingCart shoppingCart = shoppingCartRepository.findByCustomerAndAuthorizedTrue(customer);

        if (shoppingCart == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }
}
