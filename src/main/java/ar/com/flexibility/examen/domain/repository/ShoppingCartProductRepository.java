package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.ShoppingCartProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartProductRepository extends CrudRepository <ShoppingCartProduct, Long> {


}
