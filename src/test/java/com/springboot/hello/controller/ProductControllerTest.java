package com.springboot.hello.controller;

import com.google.gson.Gson;
import com.springboot.hello.dto.ProductDto;
import com.springboot.hello.dto.ProductResponseDto;
import com.springboot.hello.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;

@WebMvcTest(ProductController.class)    //웹에서 사용되는 요청과 응답에 대한 테스트 수행, @SpringBootTest보다 가볍게 테스트 하기 취해 사용
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean   // 실제 빈 객체가 아닌 가짜 객체를 생성해서 주입하는 역할, given() 메서드를 통해 동작을 정의해야 함
    ProductServiceImpl productService;

    @Test   // 테스트 코드가 포함돼 있다고 선언하는 어노테이션, JUnit Jupiter에서 감지해서 테스트 계획에 포함시킴
    @DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception {

        given(productService.getProduct(123L)).willReturn(
                ProductResponseDto.builder()
                        .number(123L)
                        .name("pen")
                        .stock(5000)
                        .price(2000)
                        .build()
        );

        String productId = "123";

        mockMvc.perform(
                        get("/product?number=" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).getProduct(123L);
    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception {
        //Mock 객체에서 특정 메서드가 실행되는 경우 실제 Return을 줄 수 없기 때문에 아래와 같이 가정 사항을 만들어줌
        given(productService.saveProduct(new ProductDto("pen", 5000, 2000)))
                .willReturn(ProductResponseDto.builder()
                        .number(123L)
                        .name("pen")
                        .price(5000)
                        .stock(2000)
                        .build()
                );

        ProductDto productDto = new ProductDto("pen", 5000, 2000);

        Gson gson = new Gson();
        String content = gson.toJson(productDto);

        mockMvc.perform(
                        post("/product")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).saveProduct(new ProductDto("pen", 5000, 2000));

    }


}
