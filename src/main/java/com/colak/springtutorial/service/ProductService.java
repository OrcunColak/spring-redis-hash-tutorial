package com.colak.springtutorial.service;

import com.colak.springtutorial.dao.ProductDao;
import com.colak.springtutorial.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDAO;

    public Product save(Product product){
        return productDAO.save(product);
    }

    public List<Product> findAll(){
        return productDAO.findAll();
    }

    public Product findProductById(String id){
        return productDAO.findProductById(id);
    }

    public String delete(String id){
        return productDAO.deleteProduct(id);
    }
}
