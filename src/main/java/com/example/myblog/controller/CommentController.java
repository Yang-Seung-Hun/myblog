package com.example.myblog.controller;

import com.example.myblog.domain.Comment;
import com.example.myblog.domain.Post;
import com.example.myblog.domain.RequestCommentDto;
import com.example.myblog.domain.RequestPostDto;
import com.example.myblog.repository.CommentRepository;
import com.example.myblog.repository.PostRepository;
import com.example.myblog.service.CommentService;
import com.example.myblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @GetMapping("/api/newComment/post/{id}")
    public String saveForm(@PathVariable Long id, Model model){
        model.addAttribute("requestForm", new RequestCommentDto());
        return "writeComment";
    }

    @PostMapping("/api/newComment/post/{id}")
    public String save(@PathVariable Long id, RequestCommentDto requestForm, Model model){
        Post post = postRepository.findPostById(id);

        if (!requestForm.getContent().equals("")){
            Comment comment = new Comment(requestForm);
            comment.setPost(post);
            commentService.save(comment);
        }

        List<Comment> comments = commentRepository.findAllByModifiedAtDesc(id);

        model.addAttribute("post",post);
        model.addAttribute("comments",comments);
        return "postPage";
    }

    @GetMapping("/update/comment/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        Comment comment = commentRepository.findCommentById(id);
        model.addAttribute("requestForm",new RequestCommentDto(comment.getName(),comment.getContent()));
        return "commentUpdateForm";
    }

    @PostMapping("/update/comment/{id}")
    public String update(@PathVariable Long id, RequestCommentDto requestForm, Model model, RedirectAttributes rttr){

         Comment comment;

        if(!requestForm.getContent().equals("")) comment = commentService.update(id, requestForm);
        else comment = commentRepository.findCommentById(id);

        Post post = comment.getPost();
        Long post_id = post.getId();
        List<Comment> comments = commentRepository.findAllByModifiedAtDesc(post_id);

        model.addAttribute("post",post);
        model.addAttribute("comments",comments);

        return "postPage";
    }

    @GetMapping("/delete/comment/{id}")
    public String delete(@PathVariable Long id, Model model){
        Comment comment = commentRepository.findCommentById(id);
        commentRepository.delete(comment);

        Post post = comment.getPost();
        Long post_id = post.getId();

        List<Comment> comments = commentRepository.findAllByModifiedAtDesc(post_id);

        model.addAttribute("post",post);
        model.addAttribute("comments", comments);

        return "postPage";
    }

}
