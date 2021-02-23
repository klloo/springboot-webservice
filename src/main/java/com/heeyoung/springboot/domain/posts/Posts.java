package com.heeyoung.springboot.domain.posts;


import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //기본 생성자 자동추
@Entity //테이블과 링크될 클래스임을 나타냄
public class Posts extends BaseTimeEntity {

    @Id //해당 필드의 pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //pk의 생성 규칙 스프링부트2.0에서는 identity추가해야만 auto_increment가
    private Long id;

    @Column(length =  500,nullable = false) //테이블의 칼럼 나타냄 선언 안해도 해당 클래스의 필드는 모두 칼럼이 된다
    //사용하는 이유는 기본 값 외에 추가로 변경이 필요한 옵션이 있으면 사용..문자열은 기본값이 VARCHAR(255)인데 500으로 늘리거나
    //타입을 TEXT로 변경하고 싶을 때 사용
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;
    private String author;
    private String email;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성 생성자에 포함된 필드만 빌더 포
    public  Posts(String title,String content,String author,String email){
        this.title = title;
        this.content = content;
        this.author = author;
        this.email = email;
    }

    public void update(String title,String content){
        this.title = title;
        this.content = content;
    }

}
