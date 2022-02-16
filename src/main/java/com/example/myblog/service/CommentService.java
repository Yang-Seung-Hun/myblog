package com.example.myblog.service;

import com.example.myblog.domain.Comment;
import com.example.myblog.domain.RequestCommentDto;
import com.example.myblog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void save(Comment comment){
        commentRepository.save((comment));
    }

    @Transactional
    public Comment update(Long id, RequestCommentDto requestCommentDto){
        Comment comment = commentRepository.findCommentById(id);
        comment.update(requestCommentDto);
        return comment;
    }

}
