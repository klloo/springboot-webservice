package com.heeyoung.springboot.web;


import com.heeyoung.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//컨트롤를 JSON을 반환하는 컨트롤러로 만들어준다
@RestController
public class HelloController {

    //get요청을 맏을 수 있는 API를 만들어준다
    @GetMapping("/hello")
    public  String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount){
        return new HelloResponseDto(name,amount);
    }
}
