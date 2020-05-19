
package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShoppingCart {
    public ShoppingCart() {
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ShoppingCartProduct> products;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ShoppingCartProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ShoppingCartProduct> products) {
        this.products = products;
    }
}


