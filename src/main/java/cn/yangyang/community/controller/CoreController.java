package cn.yangyang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoreController {
    @GetMapping("/core")
    public String Core(){
        return "core";
    }
}
