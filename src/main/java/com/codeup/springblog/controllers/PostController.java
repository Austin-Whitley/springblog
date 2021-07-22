package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String viewPosts(Model model){

        Post p1 = new Post("Whats up with this weather?!", "It's been over 90 degrees for like a week now. When will this all end? I'm getting pretty sick of the heat.");
        Post p2 = new Post("Rubber ducks", "I found this thrift store in town, and ill tell you what if, if you're into rubber ducks at all I would highly recommend checking it out. They have a sale going on right now buy 5 get 5 free! and the ducks are 50c a pop! What a steal!!");

        List<Post> posts = new ArrayList<>();
        posts.add(p1);
        posts.add(p2);

        model.addAttribute("posts", posts);

        return "posts/index";
    }

    @GetMapping("/join")
    public String showJoinForm(){
        return "join";
    }

    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model){
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        return "join";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewPost(@PathVariable long id){
        return "Viewing a post with the id of: " + String.valueOf(id);
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "view the post forum.";
    }

    @PostMapping("posts/create")
    @ResponseBody
    public String submitPost(){
        return "Create a new post";
    }

}
