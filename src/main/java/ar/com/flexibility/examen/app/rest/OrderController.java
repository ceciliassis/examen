package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.ShoppingCart;
import ar.com.flexibility.examen.domain.model.ShoppingCartProduct;
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

    //    Method: GET
    //    Route: orders/customer/{customerId}
    //    Description: Returns all customer's shopping carts
    @GetMapping("/customer/{customerId}")
    public ResponseEntity <Iterable <ShoppingCart>> findAllByCustomer(@PathVariable Long customerId) {
        //  TODO - suelen: Validar se cliente ou não
        Customer customer = customerRepository.findOne ( customerId );
        return new ResponseEntity <> ( shoppingCartRepository.findByCustomer ( customer ), HttpStatus.OK );
    }


    //    Method: GET
    //    Route: customers/{customerId}/cart
    //    Description: Returns customer's current shopping cart
    @GetMapping("/customer/{customerId}/active")
    public ResponseEntity <ShoppingCart> findActiveShoppingCart(@PathVariable Long customerId) {
        Customer customer = customerRepository.findOne ( customerId );

        return new ResponseEntity <> ( shoppingCartRepository.findByCustomerAndAuthorizedFalse ( customer ), HttpStatus.OK );
    }

    //    Method: PATCH
    //    Route: customers/{customerId}/cart
    //    Description: Update shopping cart's products
    @PatchMapping("/customer/{customerId}/active")
    public ResponseEntity <ShoppingCart> updateShoppingCart(@PathVariable Long customerId, @RequestBody List <ShoppingCartProduct> shoppingCartProduct) {
        Customer customer = customerRepository.findOne ( customerId );
        ShoppingCart activeShoppingCart = shoppingCartRepository.findByCustomerAndAuthorizedFalse ( customer );

        if (activeShoppingCart == null) {
           activeShoppingCart = shoppingCartRepository.save ( new ShoppingCart (customer) );

        }

        activeShoppingCart.setShoppingCartProduct ( shoppingCartProduct );
        shoppingCartRepository.save ( activeShoppingCart );

        return new ResponseEntity <> ( activeShoppingCart, HttpStatus.OK );
    }

}
