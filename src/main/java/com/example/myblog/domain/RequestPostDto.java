package com.example.myblog.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestPostDto {

    private String title;
    private String name;
    private String content;

    public RequestPostDto(String title, String name, String content) {
        this.title = title;
        this.name = name;
        this.content = content;
    }
}
