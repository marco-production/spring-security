package com.store.electronic.repository;

import com.store.electronic.model.Product;
import com.store.electronic.model.ProductStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //@PersistenceContext
    //EntityManager entityManager;

    /*public List<Product> getProductList() {
        var query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
    }*/

    public List<Product> getProductByStatus(ProductStatus status);

    @Query("SELECT p from Product p JOIN p.user u where u.username = :username")
    public List<Product> findAllByUser(@Param("username") String username);
}
