package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends CrudRepository <ShoppingCart, Long>{
}
