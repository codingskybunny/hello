package com.springboot.hello.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor //Lombok 모든 인수를 받는 생성자 자동 생성
@Getter //Lombok Getter 클래스 자동 생성
@Setter //Lombok Setter 클래스 자동 생성
public class ProductDto {

    private String name;
    private int price;
    private int stock;

}
