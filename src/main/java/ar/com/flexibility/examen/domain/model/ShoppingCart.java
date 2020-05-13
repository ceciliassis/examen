
package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;

@Entity
public class ShoppingCart {

    public ShoppingCart() {
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;


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


