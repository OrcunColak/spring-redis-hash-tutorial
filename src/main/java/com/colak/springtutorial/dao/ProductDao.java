package com.colak.springtutorial.dao;

import com.colak.springtutorial.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductDao {

    public static final String HASH_KEY = "Product";

    private final RedisTemplate<String, Product> redisProductTemplate;


    public Product save(Product product) {
        // Convert id to String
        redisProductTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        List<Object> objects = redisProductTemplate.opsForHash().values(HASH_KEY);
        return objects.stream()
                .map(object -> (Product) object)
                .collect(Collectors.toList());
    }

    public Product findProductById(String id) {
        // Convert id to String
        String hashKey = String.valueOf(id);
        Object object = redisProductTemplate.opsForHash().get(HASH_KEY, hashKey);
        return (Product) object;
    }

    public String deleteProduct(String id) {
        // Convert id to String
        String hashKey = String.valueOf(id);
        redisProductTemplate.opsForHash().delete(HASH_KEY, hashKey);
        return "product removed !!";
    }
}
