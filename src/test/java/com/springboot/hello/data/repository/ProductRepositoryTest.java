package com.springboot.hello.data.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.hello.data.entity.Product;
import com.springboot.hello.data.entity.QProduct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@DataJpaTest    // 기본적으로 내장된 메모리 데이터베이스를 이용해 테스트를 실행(환경은 내장/테스트는 물리 불일치 에러 발생) -> 엔티티들과 EntityManager 정도만 등록
@SpringBootTest //@Configuration으로 등록한 @Bean을 쓰고싶으면 @SpringBootTest를 써야함
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 데이터소스를 내장으로 교체하지 않음
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //라이프 사이클을 클래스 단위로 설정, 라이프 사이클을 클래스 단위로 지정해 놓으면 @BeforeAll, @AfterAll 어노테이션을 static method가 아닌 곳에서도 사용가능
public class ProductRepositoryTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @BeforeAll
    void setup() {
        Product product1 = new Product();
        product1.setName("사인펜");
        product1.setPrice(2000);
        product1.setStock(100);

        Product product2 = new Product();
        product2.setName("수성펜");
        product2.setPrice(3000);
        product2.setStock(200);

        Product product3 = new Product();
        product3.setName("모나미펜");
        product3.setPrice(1000);
        product3.setStock(300);

        Product product4 = new Product();
        product4.setName("지우개");
        product4.setPrice(1000);
        product4.setStock(300);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
    }

    @Test
    void queryDslTest() {

        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query
                .from(qProduct)
                .where(qProduct.name.contains("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList) {
            System.out.println(product.getNumber() + " " + product.getName() + " " + product.getPrice() + " " + product.getStock());
        }

    }

    @Test
    void queryDslTest2() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.name.contains("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList) {
            System.out.println(product.getNumber() + " " + product.getName() + " " + product.getPrice() + " " + product.getStock());
        }

    }

    @Test
    void queryDslTest3() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.contains("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String product : productList) {
            System.out.println(product);
        }

    }

    @Test
    void queryDslTest4() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Tuple> productList = jpaQueryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.contains("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Tuple product : productList) {
            System.out.println(product.get(qProduct.name) + " " + product.get(qProduct.price));
        }

    }

    @Test
    void queryDslTest5() {

        QProduct qProduct = QProduct.product;

        List<Tuple> productList = jpaQueryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.contains("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Tuple product : productList) {
            System.out.println(product.get(qProduct.name) + " " + product.get(qProduct.price));
        }

    }

}
