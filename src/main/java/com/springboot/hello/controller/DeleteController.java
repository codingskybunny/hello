package com.springboot.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {

    //@PathVariable과 @RequestParam을 활용한 DELETE 메서드 구현
    @DeleteMapping(value = "/{var}")
    public String DeleteVariable(@PathVariable String var) {
        return var;
    }

    //@RequestParam을 활용한 DELETE 메서드 구현
    @DeleteMapping(value = "/request1")
    public String getRequestParam1(@RequestParam String email) {
        return "email : " + email;
    }

}
