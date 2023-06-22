package com.springboot.hello.data.entity;

import com.springboot.hello.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaAuditingTest {

    @Autowired
    public ProductRepository productRepository;

    @Test
    public void auditingTest() {

        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(123);

        Product savedProduct = productRepository.save(product);

        System.out.println(savedProduct.getName());
        System.out.println(savedProduct.getCreatedAt());


    }

}
