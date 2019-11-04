package cn.yangyang.community.controller;

import cn.yangyang.community.mapper.LoginRecoedMapper;
import cn.yangyang.community.mapper.UserMapper;
import cn.yangyang.community.pojo.LoginRecoed;
import cn.yangyang.community.pojo.User;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginRecoedMapper recoedMapper;

    //判断账号
    @GetMapping("/JudgementAccount")
    public String JudgementAccount(@RequestParam(value = "username",defaultValue = "15807247638") String username,
                                   @RequestParam(value = "password",defaultValue = "123456") String password,
                                   HttpServletResponse response){
        User user = userMapper.JudgementAccount(new User(username,password));
        if (user!=null){
            try {
                LoginRecoed recoed=new LoginRecoed();
                recoed.setUser_ID(user.getUser_ID());
                Date date = new Date();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                recoed.setRecoed_Datetime(dateFormat.format(date));
                recoedMapper.insert(recoed);
            }
            catch (Exception e){
                System.out.println("添加失败");
                e.printStackTrace();
            }
            if (null!=user.getUser_Token()&&redisTemplate.hasKey(user.getUser_Token())) redisTemplate.delete(user.getUser_Token());
            String token=UUID.randomUUID().toString();
            user.setUser_Token(token);
            Integer n= userMapper.UpdateByToken(user);
            if(n>0){
                //效率不行
                redisTemplate.opsForValue().set(token,JSON.toJSONString(user),0);
                Cookie cookie = new Cookie("token",token);
                response.addCookie(cookie);
            }
        }
        return "redirect:/";
    }
}
