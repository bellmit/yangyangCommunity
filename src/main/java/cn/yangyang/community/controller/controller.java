package cn.yangyang.community.controller;

import cn.yangyang.community.mapper.LoginRecoedMapper;
import cn.yangyang.community.mapper.UserMapper;
import cn.yangyang.community.pojo.User;
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
    private UserMapper userMapper;

    @Autowired
    private LoginRecoedMapper recoedMapper;

    @GetMapping("/")
    public String pagehome(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("user")==null){
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token=cookie.getValue();
                    if (redisTemplate.hasKey(token)){
                        //json字符串转对象
                        User user = JSON.parseObject(redisTemplate.opsForValue().get(token).toString(), User.class);
                        if (null!=user) session.setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        return "pagehome";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
