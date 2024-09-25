package com.colak.springtutorial.config;

import com.colak.springtutorial.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    // RedisConnectionFactory is an interface. The passed in object type is LettuceConnectionFactory
    @Bean
    public RedisTemplate<String, Product> redisProductTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Product> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // Set serializers
        template.setKeySerializer(new StringRedisSerializer());
        Jackson2JsonRedisSerializer<Product> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Product.class);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }
}
