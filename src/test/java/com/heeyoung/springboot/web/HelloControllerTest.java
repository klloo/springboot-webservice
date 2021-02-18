package com.heeyoung.springboot.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//테스트를 진행할 때 제이유닛에 내장된 실행자 외에 다른 실행자를 실행시킨다
//여기서는 spingrunner라는 스프링 실행자 사용
//즉 스프링부트테스트와 제이유닛 사이의 연결자 역할을 한다.
//webmvctest는 web에 집중할 수 있는 어노테이션...
@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    //웹 api를 테스트할 떄 사용 스프링 mvc테스트의 시작점으로 이 클래스를 통해 HTTP,GET,POST등에 대한 API를 테스트할 수 있
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //mockmvc를 통해 /hello주소로 http get 요청을 한다
                .andExpect(status().isOk()) //mvc.perform의 결과를 검증..HTTP헤더의 status를 검증 200 400 이런거 여기선 ok인지 아닌지 검
                .andExpect(content().string(hello)); //응답본문의 내용을 검증 controller에서 hello를 리턴하니까 이 값이 맞는지 검

    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                    get("/hello/dto")
                .param("name",name) //요청파라미터 설정
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }


}
