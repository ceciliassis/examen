package ar.com.flexibility.examen.domain.model;

import ar.com.flexibility.examen.domain.repository.ShoppingCartRepository;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class Customer {


    public Customer() {
    }

    public Customer(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
    }


//    CAN I CREATE A GET ACTIVE SHOPPING CART AS A METHOD FOR CUSTOMER??
//    public List <ShoppingCart> getActiveShoppingCart(){
//
//        return shoppingCartRepository.findAll().stream ()
//                .filter (shoppingCart -> shoppingCart.getCustomer() == this )
//                .filter(shoppingCart -> !shoppingCart.isAuthorized ())
//                .collect ( toList() );
//    }


}
