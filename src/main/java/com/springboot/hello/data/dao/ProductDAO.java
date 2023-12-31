package com.springboot.hello.data.dao;

import com.springboot.hello.data.entity.Product;

//interface = 설계도
//implement = 구현체(설계도를 따라서)
public interface ProductDAO {

    Product insertProduct(Product product);

    Product selectProduct(Long number);

    Product updateProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;

}
