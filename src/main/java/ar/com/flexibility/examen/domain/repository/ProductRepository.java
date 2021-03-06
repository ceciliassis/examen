package ar.com.flexibility.examen.domain.repository;


import ar.com.flexibility.examen.domain.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository <Product, Long> {

}
