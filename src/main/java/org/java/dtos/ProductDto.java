package org.java.dtos;

import org.java.enums.Category;
import org.java.models.Product;

public class ProductDto {
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;
    private Category category;

    public ProductDto(Integer id, String name, Double price, Integer quantity, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public ProductDto(String name, Double price, Integer quantity, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public ProductDto(Product product, Integer quantity) {
        this.id = product.getProductId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = quantity;
        this.category = product.getCategory();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category +
                '}';
    }
}
