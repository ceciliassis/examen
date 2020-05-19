package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.ShoppingCart;
import ar.com.flexibility.examen.domain.model.ShoppingCartProduct;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.repository.ShoppingCartProductRepository;
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
    private ShoppingCartProductRepository shoppingCartProductRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Iterable<ShoppingCart>> findAllByCustomer(@PathVariable Long customerId) {
        if (!customerRepository.exists(customerId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Customer customer = customerRepository.findOne(customerId);

        return new ResponseEntity<>(shoppingCartRepository.findByCustomer(customer), HttpStatus.OK);
    }

    @GetMapping("customer/{customerId}/active")
    public ResponseEntity<ShoppingCart> getCustomerActiveShoppingCart(@PathVariable Long customerId) {
        if (!customerRepository.exists(customerId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Customer customer = customerRepository.findOne(customerId);
        ShoppingCart shoppingCart = shoppingCartRepository.findByCustomerAndAuthorizedFalse(customer);

        if (shoppingCart == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @PatchMapping("customer/{customerId}/active")
    public ResponseEntity<ShoppingCart> updateShoppingCart(
            @PathVariable Long customerId, @RequestBody List<ShoppingCartProduct> shoppingCartProducts
    ) {
        if (!customerRepository.exists(customerId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Customer customer = customerRepository.findOne(customerId);
        ShoppingCart shoppingCart = shoppingCartRepository.findByCustomerAndAuthorizedFalse(customer);

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart(customer);
            shoppingCartRepository.save(shoppingCart);
        }

        for (ShoppingCartProduct shoppingCartProduct : shoppingCartProducts) {
            ShoppingCartProduct currentProduct = shoppingCartProductRepository.findByShoppingCartAndProduct(
                    shoppingCart,
                    shoppingCartProduct.getProduct());

            if (currentProduct == null) {
                shoppingCartProduct.setShoppingCart(shoppingCart);
                currentProduct = shoppingCartProduct;
            } else {
                currentProduct.setQuantity(shoppingCartProduct.getQuantity());
            }

            shoppingCartProductRepository.save(currentProduct);
        }

        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

//    TODO: add checkout method


}
