package com.example.myblog.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestCommentDto {

    private String name;

    private String content;

    public RequestCommentDto(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
