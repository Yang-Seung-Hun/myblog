package com.example.myblog.service;

import com.example.myblog.domain.Post;
import com.example.myblog.domain.RequestPostDto;
import com.example.myblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void save(Post post){
        postRepository.save(post);
    }

    @Transactional
    public Post update(Long id, RequestPostDto requestPostDto){
        Post post = postRepository.findPostById(id);
        post.update(requestPostDto);
        return post;
    }
}
