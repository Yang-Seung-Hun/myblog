package com.example.myblog.repository;

import com.example.myblog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    public Comment findCommentById(Long id);

    @Query("select c from Comment c left join c.post p where p.id =:post_id order by c.modifiedAt desc")
    public List<Comment> findAllByModifiedAtDesc(@Param("post_id") Long id);


}
