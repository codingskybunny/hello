package com.springboot.hello.service.impl;

import com.springboot.hello.data.dao.ProductDAO;
import com.springboot.hello.data.entity.Product;
import com.springboot.hello.dto.ProductDto;
import com.springboot.hello.dto.ProductResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ProductServiceTest {

    private ProductDAO productDAO = Mockito.mock(ProductDAO.class);
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUpTest() {
        productService = new ProductServiceImpl(productDAO);
    }

    @Test
    void getProductTest() {
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("펜");
        givenProduct.setPrice(1000);
        givenProduct.setStock(1234);

        Mockito.when(productDAO.selectProduct(123L))
                .thenReturn(givenProduct);

        ProductResponseDto productResponseDto = productService.getProduct(123L);

        Assertions.assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        Assertions.assertEquals(productResponseDto.getName(), givenProduct.getName());
        Assertions.assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(productResponseDto.getStock(), givenProduct.getStock());

        verify(productDAO).selectProduct(123L);

    }

    @Test
    void saveProductTest() {

        //any() : 모든 매개 변수(인수)에 대하여 같은 행동을 하는 Mock 객체를 생성
        Mockito.when(productDAO.insertProduct(any(Product.class)))
                .then(returnsFirstArg());

        ProductResponseDto productResponseDto = productService.saveProduct(
                new ProductDto("펜", 1000, 1234)
        );

        Assertions.assertEquals(productResponseDto.getName(), "펜");
        Assertions.assertEquals(productResponseDto.getPrice(), 1000);
        Assertions.assertEquals(productResponseDto.getStock(), 1234);

        verify(productDAO).insertProduct(any());

    }


}
