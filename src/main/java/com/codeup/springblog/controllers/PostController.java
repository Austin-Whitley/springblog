package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.print.attribute.standard.PresentationDirection;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailDao;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailDao){
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailDao = emailDao;
    }

    @GetMapping("/posts")
    public String viewPosts(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) throws EntityNotFoundException {
        try {
            Post post = postDao.getById(id);
            boolean isPostOwner = false;
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
                User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                isPostOwner = currentUser.getId() == post.getUser().getId();
            }
            model.addAttribute("post", post);
            model.addAttribute("isPostOwner", isPostOwner);
            return "posts/show";
        }catch(EntityNotFoundException enf){
            return "redirect:/posts";
        }
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postDao.getById(id);
        if(currentUser.getId() == post.getUser().getId()){
            model.addAttribute("post", post);
            return "posts/editPost";
        }else{
            return  "redirect:/posts/" + id;
        }
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post post ) {
        return createPost(post);
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
        postDao.delete(postDao.getById(id));
        return "redirect:/posts";
    }


    @GetMapping("/posts/create")
    public String showCreateForm(Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(currentUser != null){
            model.addAttribute("post", new Post());
            return "posts/create";
        }
        return "redirect:/posts";
    }

    //create a post
    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(currentUser);
        postDao.save(post);
        emailDao.prepareAndSend(post, "Post submitted!", "Post title: " + post.getTitle() + "\nPost body: " + post.getBody());
        return "redirect:/posts";
    }



}
