package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends CrudRepository <ShoppingCart, Long> {
//    TODO: remove because its not necessary
    List <ShoppingCart> findAll();

//    List<User> findByEmailAddressAndLastname(String emailAddress, String lastname);
    Iterable<ShoppingCart> findByCustomer(Customer customer);

    ShoppingCart findByCustomerAndAuthorizedFalse(Customer customer);
}
