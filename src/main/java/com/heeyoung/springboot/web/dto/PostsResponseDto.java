package com.heeyoung.springboot.web.dto;

import com.heeyoung.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String modifiedDate;
    private String email;

    public PostsResponseDto(Posts entity) {
        String formatDate = entity.getModifiedDate()
                .format(DateTimeFormatter.ofPattern("yyyy. M. dd. h:mm a",new Locale("en", "US")));
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.email = entity.getEmail();
        this.modifiedDate = formatDate;
    }
}
