package com.example.myblog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped{

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String name;

    private String content;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Post(RequestPostDto requestPostDto){
        this.title = requestPostDto.getTitle();
        this.name = requestPostDto.getName();
        this.content = requestPostDto.getContent();
    }

    public Post(String title, String name, String content) {
        this.title = title;
        this.name = name;
        this.content = content;
    }

    public void update(RequestPostDto requestPostDto){
        this.title = requestPostDto.getTitle();
        this.name = requestPostDto.getName();
        this.content = requestPostDto.getContent();
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
        comment.putComment(this);
    }
}
