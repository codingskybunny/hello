package com.springboot.hello.controller;

import com.springboot.hello.dto.MemberDto;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")  //class 위에 @RequestMapping 가능
public class GetController {

    //@RequestMapping은 스프링 4.3버전 이후 잘 사용되지 않음
    //@RequestMapping -> @GetMapping
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public String getHello() {
//        return "Hello World";
//    }

    //매개변수가 없는 GET 메소드 구현
    @GetMapping(value = "/name")
    public String getName() {
        return "sky bunny";
    }

    //@PathVariable을 활용한 GET 메서드 구현
    @GetMapping(value = "/var1/{var}")
    public String getVariable(@PathVariable String var) {
        return "★" + var;
    }

    //요청 Uri 변수명과 메소드 변수명이 다를 때
    @GetMapping(value = "/var2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return "★" + var;
    }

    //@RequestParam을 활용한 GET 메소드 구현
//    @GetMapping(value = "/request1")
//    public String getRequestParam1(
//            @RequestParam String name,
//            @RequestParam String email,
//            @RequestParam String organization
//    ) {
//        return name + " " + email + " " + organization;
//    }

    //Swagger 세부 내용 작성
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @Parameter(description = "이름", required = true, example = "sky bunny") String name,
            @Parameter(description = "이메일", required = true, example = "skybunny@gmail.com") String email,
            @Parameter(description = "조직", required = true, example = "home") String organization
    ) {
        return name + " " + email + " " + organization;
    }

    //Map 객체 활용
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String,String> param) {

        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        //param.forEach((key, value) -> sb.append(key).append(" : ").append(value).append("\n"));

        return sb.toString();

    }

    //DTO 객체를 활용한 GET 메서드 구현
    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDto) {
        return memberDto.getName() + " " + memberDto.getEmail() + " " + memberDto.getOrganization();
        //return memberDto.toString();
    }

}
