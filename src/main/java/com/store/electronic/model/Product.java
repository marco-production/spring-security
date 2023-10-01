package com.store.electronic.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
//@Data lombok - Para general getter, setter and constructor.
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal cost;
    private LocalDate dateLine;
    private String urlImage;
    private String urlProduct;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    public Product(){}

    public Product(String name, String description, double cost, LocalDate dateLine, String urlImage, String urlProduct) {
        this.name = name;
        this.description = description;
        this.cost = new BigDecimal(cost);
        this.dateLine = dateLine;
        this.urlImage = urlImage;
        this.urlProduct = urlProduct;
        this.status = ProductStatus.WAITING;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = new BigDecimal(cost);
    }

    public LocalDate getDateLine() {
        return dateLine;
    }

    public void setDateLine(LocalDate dateLine) {
        this.dateLine = dateLine;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUrlProduct() {
        return urlProduct;
    }

    public void setUrlProduct(String urlProduct) {
        this.urlProduct = urlProduct;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
