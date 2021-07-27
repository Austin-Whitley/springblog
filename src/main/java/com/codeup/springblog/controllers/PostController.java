package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostRepository;
import com.codeup.springblog.models.User;
import com.codeup.springblog.models.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String viewPosts(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "/posts/index";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model){
        return "posts/create";
    }

    //create a post
    @PostMapping("/posts/create")
    public String createPost(@RequestParam String title, @RequestParam String body){
        User user = userDao.getById(1L);
        Post post = new Post(title, body, user);
        postDao.save(post);
        return "redirect:/posts";
    }

    //postMapping for delete
    @PostMapping("/post/delete")
    public String deletePost(@RequestParam("deletePost") long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    //get the post
    @GetMapping("post/edit")
    public String editPost(@RequestParam("editPost") long id, Model model){
        model.addAttribute("post", postDao.findById(id));
        return "/posts/editPost";
    }

    @PostMapping("/post/edit")
    public String editPost(@RequestParam("postId") long postId, @RequestParam("postTitle") String title, @RequestParam("postBody") String body){
//        Post post = new Post(postId, title, body);
//        postDao.save(post);
        return "redirect:/posts";
    }

//    @GetMapping("/join")
//    public String showJoinForm(){
//        return "join";
//    }
//
//    @PostMapping("/join")
//    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model){
//        model.addAttribute("cohort", "Welcome to " + cohort + "!");
//        return "join";
//    }

//    @GetMapping("/posts/{id}")
//    @ResponseBody
//    public String viewPost(@PathVariable long id){
//        return "Viewing a post with the id of: " + String.valueOf(id);
//    }
//
//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String createPost(){
//        return "view the post forum.";
//    }
//
//    @PostMapping("posts/create")
//    @ResponseBody
//    public String submitPost(){
//        return "Create a new post";
//    }

}
