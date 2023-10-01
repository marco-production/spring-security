package com.store.electronic.controller;

import com.store.electronic.dto.RequestNewProduct;
import com.store.electronic.model.Product;
import com.store.electronic.repository.ProductRepository;
import com.store.electronic.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@RequestMapping("/pedido")
// @RequestMapping(method = RequestMethod.GET, value="formulario")
public class OrderController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/add-product")
    public String create(Model model) {
        model.addAttribute("requestNewProduct", new RequestNewProduct());
        return "views/create";
    }

    @PostMapping("/add-product")
    public String store(@Valid RequestNewProduct request, BindingResult result) {
        if(result.hasErrors()) {
            return "views/create";
        }

        // Get current Auth User
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Product product = request.toProduct();
        product.setUser(userRepository.findUserByUsername(username));

        productRepository.save(product);
        return "redirect:/products";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:/products";
    }
}
