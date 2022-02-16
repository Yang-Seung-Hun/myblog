package com.example.myblog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends Timestamped{

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String name;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void putComment(Post post){
        this.post = post;
    }

    public Comment(RequestCommentDto requestCommentDto){
        this.name = requestCommentDto.getName();
        this.content = requestCommentDto.getContent();
    }

    public Comment(Long id, String name, String content, Post post) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.post = post;
    }

    public void setPost(Post post){
        this.post = post;
        post.getComments().add((this));
    }

    public void update(RequestCommentDto requestCommentDto){
        this.name = requestCommentDto.getName();
        this.content = requestCommentDto.getContent();
    }
}
