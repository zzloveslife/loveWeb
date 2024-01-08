package com.echoes.mysite.controller;

import com.echoes.mysite.service.HelloService;

import com.echoes.mysite.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Resource
    private HelloService helloService;

    @GetMapping("/hello")
    @ResponseBody
    public Result hello() {
        String hello = helloService.hello();
        Result<String> result = new Result<>(200, "Success", hello);
        return result;
    }
}
