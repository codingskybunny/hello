package com.springboot.hello.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor //Lombok 모든 인수를 받는 생성자 자동 생성
@Getter //Lombok Getter 클래스 자동 생성
@Setter //Lombok Setter 클래스 자동 생성
@EqualsAndHashCode //hashcode() : 두 객체의 내부의 값이 같은지 숫자로 확인 / equals() : 같은 객체인지 확인하는 메소드
public class ProductDto {

    private String name;
    private int price;
    private int stock;

}
