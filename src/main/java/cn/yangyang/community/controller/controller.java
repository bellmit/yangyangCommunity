package cn.yangyang.community.controller;

import cn.yangyang.community.dto.PageDto;
import cn.yangyang.community.pojo.User;
import cn.yangyang.community.service.PublishService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class controller {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PublishService publishService;

    @GetMapping("/")
    public String pagehome(@RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize,
                           HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    if (redisTemplate.hasKey(token)) {
                        //json字符串转对象
                        User user = JSON.parseObject(redisTemplate.opsForValue().get(token).toString(), User.class);
                        if (null != user) {
                            session.setAttribute("user", user);
                        }
                    }
                    break;
                }
            }
        }
        PageDto pageDto = publishService.list(page, pagesize);
        model.addAttribute("datas", pageDto);
        model.addAttribute("showlogin",true);
        model.addAttribute("showpublish",true);
        return "pagehome";
    }


    @GetMapping("/quit")
    public String quit(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    if (redisTemplate.hasKey(token)) {
                        redisTemplate.delete(token);
                    }
                    break;
                }
            }
        }
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
