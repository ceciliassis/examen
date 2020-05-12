package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @PostMapping("")
    public ResponseEntity <?> createProduct(@RequestBody Product product) {

        if (product.getPrice () == 0 || product.getDescription ().isEmpty () || product.getName ().isEmpty ()) {
            return new ResponseEntity <> ( "MISSING INFO", HttpStatus.NOT_ACCEPTABLE );
        }

        productRepository.save ( product );
        return new ResponseEntity <> ( "PRODUCT " + product.getId ().toString () + " CREATED", HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteCustomer(@PathVariable Long id) {

        if (!productRepository.exists ( id )) {
            return new ResponseEntity <> ( "PRODUCT " + id.toString () + " NOT FOUND", HttpStatus.NOT_FOUND );
        }
        productRepository.delete ( id );
        return new ResponseEntity <> ( "PRODUCT " + id.toString () + " DELETED", HttpStatus.OK );
    }

    @PatchMapping("/{id}")
    public ResponseEntity <?> updateProduct(@PathVariable Long id, @RequestBody Product product) {

        if (!productRepository.exists ( id )) {
            return new ResponseEntity <> ( "PRODUCT " + id.toString () + " NOT FOUND", HttpStatus.NOT_FOUND );
        }

        Product currentProduct = productRepository.findOne ( id );
        if (!product.getName ().isEmpty ()) {
            currentProduct.setName ( product.getName () );
        }
        if (product.getPrice () != 0) {
            currentProduct.setPrice ( product.getPrice () );
        }
        if (!product.getDescription ().isEmpty ()) {
            currentProduct.setDescription ( product.getDescription () );
        }

        productRepository.save ( currentProduct );
        return new ResponseEntity <> ( "PRODUCT " + id.toString () + " UPDATED", HttpStatus.OK );
    }


    @GetMapping("")
    public Iterable <Product> findAll() {
        return productRepository.findAll ();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findOne ( id );
    }
}