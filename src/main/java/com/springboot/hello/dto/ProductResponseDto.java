package com.springboot.hello.dto;

import lombok.*;

@Builder    //Lombok Builder 패턴 메서드 자동 생성
@Getter //Lombok Getter 클래스 자동 생성
@Setter //Lombok Setter 클래스 자동 생성
public class ProductResponseDto {

    private Long number;
    private String name;
    private int price;
    private int stock;

}
