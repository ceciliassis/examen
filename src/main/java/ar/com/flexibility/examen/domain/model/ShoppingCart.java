
package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShoppingCart {


    //empty constructor for Spring
    public ShoppingCart() {
    }

    public ShoppingCart(Customer customer) {
        this.customer = customer;
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List <ShoppingCartProduct> shoppingCartProduct;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List <ShoppingCartProduct> getShoppingCartProduct() {
        return shoppingCartProduct;
    }

    public void setShoppingCartProduct(List <ShoppingCartProduct> shoppingCartProduct) {
        this.shoppingCartProduct = shoppingCartProduct;
    }

    private boolean authorized = false;

    public Long getId() {
        return id;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }


}


