package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShoppingCartProduct {

    public ShoppingCartProduct() {
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="shopping_cart_id")
    private ShoppingCart shoppingCart;

    private int quantity;

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
