package cn.yangyang.community.controller;

import cn.yangyang.community.dto.PageDto;
import cn.yangyang.community.pojo.User;
import cn.yangyang.community.service.PublishService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CoreController {

    @Autowired
    private PublishService publishService;

    @GetMapping("/core/{action}")
    public String Core(@PathVariable(value = "action") String action, Model model,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize,
                       HttpServletRequest request){
        model.addAttribute("showlogin",false);
        model.addAttribute("showpublish",true);
        if ("question".equals(action)){
            model.addAttribute("section","question");
            model.addAttribute("title","我的问题");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            PageDto pageDto = publishService.list(user.getUser_ID(),page,pagesize);
            model.addAttribute("datas",pageDto);
        }
        return "core";
    }
}
