
package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;

@Entity
public class ShoppingCart {


    //empty constructor for Spring
    public ShoppingCart() {
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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


