package cn.yangyang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @GetMapping("/question/{id}")
    public String question(@PathVariable(value = "id") Integer id){
        return "question";
    }
}
