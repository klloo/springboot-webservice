package com.heeyoung.springboot.web.dto;

import com.heeyoung.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String modifiedDate;

    public PostsListResponseDto(Posts entity){
        String formatDate = entity.getModifiedDate()
                .format(DateTimeFormatter.ofPattern("dd MMM yyyy",new Locale("en", "US")));
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = formatDate;
    }
}
