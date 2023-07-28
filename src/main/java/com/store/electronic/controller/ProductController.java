package com.store.electronic.controller;

import com.store.electronic.model.Product;
import com.store.electronic.model.ProductStatus;
import com.store.electronic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public String index(Model model, Principal principal){
        //List<Product> productList = productRepository.findAllByUser(principal.getName());
        List<Product> productList = productRepository.findAll();
        model.addAttribute("orderList", productList);
        return "views/index";
    }

    @GetMapping("/category")
    public String productByStatus(){
        return "views/categories";
    }

    @GetMapping("/category/{status}")
    public String productByStatus(@PathVariable("status") String status, Model model){
        if(status != null) {
            List<Product> productList = productRepository.getProductByStatus(ProductStatus.valueOf(status.toUpperCase()));
            model.addAttribute("orderList", productList);
            model.addAttribute(status);
            return "views/index";
        }

        return "redirect:/category";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Product product = productRepository.getReferenceById(Long.valueOf(id));
        model.addAttribute(product);
        return "views/show";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:/products";
    }
}
