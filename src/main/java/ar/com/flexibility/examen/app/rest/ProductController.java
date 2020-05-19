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

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productRepository.findOne(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Product>> findAll() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if (product.getPrice() == 0 || product.getDescription().isEmpty() || product.getName().isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        productRepository.save(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteCustomer(@PathVariable Long id) {
        if (!productRepository.exists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Product product = productRepository.findOne(id);
        productRepository.delete(id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (!productRepository.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Product currentProduct = productRepository.findOne(id);

        if (!product.getName().isEmpty())
            currentProduct.setName(product.getName());

        if (product.getPrice() != 0)
            currentProduct.setPrice(product.getPrice());

        if (!product.getDescription().isEmpty())
            currentProduct.setDescription(product.getDescription());

        productRepository.save(currentProduct);

        return new ResponseEntity<>(currentProduct, HttpStatus.OK);
    }

}