package com.springboot.hello.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChangeProductNameDto {

    private Long number;
    private String name;

}