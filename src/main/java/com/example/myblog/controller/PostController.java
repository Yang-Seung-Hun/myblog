package com.example.myblog.controller;

import com.example.myblog.domain.Comment;
import com.example.myblog.domain.Post;
import com.example.myblog.domain.RequestPostDto;
import com.example.myblog.repository.CommentRepository;
import com.example.myblog.repository.PostRepository;
import com.example.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/api/save/post")
    public String saveForm(Model model){
        model.addAttribute("requestForm", new RequestPostDto());
        return "writePost";
    }

    @PostMapping("/api/save/post")
    public String save(RequestPostDto requestForm, Model model){
        Post post = new Post(requestForm);
        postService.save(post);

        Long post_id = post.getId();
        List<Comment> comments = commentRepository.findAllByModifiedAtDesc(post_id);

        model.addAttribute("post",post);
        model.addAttribute("comments", comments);

        return "postPage";
    }

    @GetMapping("api/update/post/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        Post post = postRepository.findPostById(id);
        model.addAttribute("requestForm", new RequestPostDto(post.getTitle(),post.getName(),post.getContent()));
        return "postUpdateForm";
    }

    @PostMapping("api/update/post/{id}")
    public String update(@PathVariable Long id, RequestPostDto requestForm, Model model){
        Post post = postService.update(id, requestForm);
        List<Comment> comments = commentRepository.findAllByModifiedAtDesc(id);
        model.addAttribute("post",post);
        model.addAttribute("comments", comments);
        return "postPage";
    }

    @GetMapping("/api/posts")
    public String posts(Model model){
        List<Post> posts = postRepository.findAllByModifiedAtDesc();
        model.addAttribute("posts",posts);
        return "posts";
    }

    @GetMapping("/api/post/{id}")
    public String post(@PathVariable Long id, Model model){
        Post post = postRepository.findPostById(id);
        List<Comment> comments = commentRepository.findAllByModifiedAtDesc(id);
        model.addAttribute("post",post);
        model.addAttribute("comments",comments);
        return "postPage";
    }

    @GetMapping("api/delete/post/{id}")
    public String delete(@PathVariable Long id){
        Post post = postRepository.findPostById(id);
        postRepository.delete(post);

        return "redirect:/";
    }
}
