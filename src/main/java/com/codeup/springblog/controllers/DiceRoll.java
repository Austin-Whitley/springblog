package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class DiceRoll {
    Random random = new Random();

    @GetMapping("/roll-dice")
    public String rollLinks(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String checkGuess(@PathVariable int n, Model model){
        int num = random.nextInt(6 - 1 + 1) + 1;
        boolean tf = (n == num);

        model.addAttribute("n", n);
        model.addAttribute("tf", tf);
        model.addAttribute("num", num);

        return "roll-check";
    }



}
