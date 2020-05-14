package ar.com.flexibility.examen.domain.model;

import ar.com.flexibility.examen.domain.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity
public class Product {


    public Product() {
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private double price;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shoppingCartProduct")
    private ShoppingCartProduct shoppingCartProduct;

    public Product(long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}