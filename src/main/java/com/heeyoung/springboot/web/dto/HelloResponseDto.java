package com.heeyoung.springboot.web.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

//require어쩌구 : 선언된 모든 final필드가 포함된 생성자를 생성
@Getter
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
