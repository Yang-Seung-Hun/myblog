package com.example.myblog.repository;

import com.example.myblog.domain.Comment;
import com.example.myblog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    public Post findPostById(Long id);

    @Query("select p from Post p order by p.modifiedAt desc")
    public List<Post> findAllByModifiedAtDesc();
}
