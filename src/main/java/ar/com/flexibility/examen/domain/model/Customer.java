package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;
import java.util.List;

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

   @OneToMany(mappedBy = "customer", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
   private List <ShoppingCart> shoppingCart;


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
}
