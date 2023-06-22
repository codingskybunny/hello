package com.springboot.hello.data.repository;

import com.querydsl.core.types.Predicate;
// import java.util.function.Predicate;
import com.springboot.hello.data.entity.Product;
import com.springboot.hello.data.entity.QProduct;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//라이프 사이클을 클래스 단위로 설정, 라이프 사이클을 클래스 단위로 지정해 놓으면 @BeforeAll, @AfterAll 어노테이션을 static method가 아닌 곳에서도 사용가능
public class QProductRepositoryTest {

    @Autowired
    QProductRepository qProductRepository;

    @Autowired
    public ProductRepository productRepository;

    @BeforeAll
    void setup() {
        Product product1 = new Product();
        product1.setName("사인펜");
        product1.setPrice(500);
        product1.setStock(111);

        Product product2 = new Product();
        product2.setName("수성펜");
        product2.setPrice(2500);
        product2.setStock(222);

        Product product3 = new Product();
        product3.setName("모나미펜");
        product3.setPrice(1000);
        product3.setStock(333);

        Product product4 = new Product();
        product4.setName("지우개");
        product4.setPrice(1500);
        product4.setStock(444);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
    }

    @Test
    public void queryDSLTest1() {
        Predicate predicate = QProduct.product.name.containsIgnoreCase("펜")
                .and(QProduct.product.price.between(1000, 2000));

        Optional<Product> foundProduct = qProductRepository.findOne(predicate);

        if (foundProduct.isPresent()) {
            Product product = foundProduct.get();
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }
    }

    @Test
    public void queryDSLTest2() {

        QProduct qProduct = QProduct.product;

        Iterable<Product> productList = qProductRepository.findAll(
                qProduct.name.contains("펜")
                        .and(qProduct.price.between(1000, 3000))
        );

        for (Product product : productList) {
            System.out.print(product.getNumber() + "/");
            System.out.print(product.getName() + "/");
            System.out.print(product.getPrice() + "/");
            System.out.println(product.getStock());
        }
    }

}
