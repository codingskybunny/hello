package com.springboot.hello.data.dao.impl;

import com.springboot.hello.data.dao.ProductDAO;
import com.springboot.hello.data.entity.Product;
import com.springboot.hello.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

//interface = 설계도
//implement = 구현체(설계도를 따라서)
@Component  //스프링이 관리하는 빈으로 등록
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product selectProduct(Long number) {
        return productRepository.getReferenceById(number);
    }

    // JPA에 update라는 키워드는 없음
    // find() 메서드를 통해 데이터베이스에서 값을 가져오고
    // 객체의 값을 변경한 다음 ** save()를 실행하면 **
    // JPA에서 더티 체크(Dirty Check)라는 변경감지를 수행함.
    // @Transactional 어노테이션이 지정되어 있으면(save 메소드안에 있음) 메소드 내 자동으로 flush() 메소드를 실행
    // 이 과정에서 변경이 감지되면 대상 객체에 해당하는 데이터베이스의 레코드를 업데이트하는 쿼리가 실행됨
    @Override
    public Product updateProductName(Long number, String name) throws Exception {

        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;
        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }

        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            productRepository.delete(product);
        } else {
            throw new Exception();
        }

    }
}
