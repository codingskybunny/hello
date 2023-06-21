package com.springboot.hello.data.repository;

import com.springboot.hello.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest    // 기본적으로 내장된 메모리 데이터베이스를 이용해 테스트를 실행(환경은 내장/테스트는 물리 불일치 에러 발생)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 데이터소스를 내장으로 교체하지 않음
public class ProductRepositoryTestByMysql {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveTest() {

        //given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1234);

        //when
        Product savedProduct = productRepository.save(product);

        //then
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getStock(), savedProduct.getStock());

    }

    @Test
    void selectTest() {

        //given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1234);

        Product savedProduct = productRepository.saveAndFlush(product);

        //when
        Product foundProduct = productRepository.findById(savedProduct.getNumber()).get();

        //then
        assertEquals(product.getName(), foundProduct.getName());
        assertEquals(product.getPrice(), foundProduct.getPrice());
        assertEquals(product.getStock(), foundProduct.getStock());


    }

    @Test
    void updateTest() {
        //given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1234);

        Product savedProduct = productRepository.saveAndFlush(product);

        //when
        Product foundProduct = productRepository.findById(savedProduct.getNumber()).orElseThrow(RuntimeException::new);
        foundProduct.setName("장난감");

        Product updateProduct = productRepository.save(foundProduct);

        //then
        assertEquals(updateProduct.getName(), "장난감");
    }

    @Test
    void deleteTest() {
        //given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1234);

        Product savedProduct = productRepository.saveAndFlush(product);
        Product foundProduct = productRepository.findById(savedProduct.getNumber()).orElseThrow(RuntimeException::new);

        //when
        productRepository.delete(savedProduct);

        //then
        assertFalse(productRepository.findById(foundProduct.getNumber()).isPresent());
    }


}
