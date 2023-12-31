package com.springboot.hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController = @Controller + @ResponseBody
@RestController //@RestController 데이터(json)를 반환하기 위해 사용
//@Controller //@Controller 뷰(html)를 반환하기 위해 사용
//@ResponseBody
public class HelloController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    //특정 uri 요청시 데이터 반환 (여기서는 /hello 요청시 "Hello World" 반환)
    @RequestMapping("/hello")
    public String hello(){

        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.error("error");

        return "Hello World";
    }

}
