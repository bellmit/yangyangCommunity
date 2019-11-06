package cn.yangyang.community.controller;

import cn.yangyang.community.mapper.LoginRecoedMapper;
import cn.yangyang.community.mapper.UserMapper;
import cn.yangyang.community.pojo.LoginRecoed;
import cn.yangyang.community.pojo.User;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
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

    @GetMapping("/login")
    public String Login(Model model){
        model.addAttribute("showlogin",false);
        model.addAttribute("showpublish",false);
        return "login";
    }

    @GetMapping("/register")
    public String Register(Model model){
        model.addAttribute("showlogin",true);
        model.addAttribute("showpublish",false);
        return "register";
    }

    //判断账号
    @PostMapping("/JudgementAccount")
    public String JudgementAccount(@RequestParam(value = "username",defaultValue = "15807247638") String username,
                                   @RequestParam(value = "password",defaultValue = "123456") String password,
                                   HttpServletResponse response,Model model){
        model.addAttribute("showlogin",false);
        model.addAttribute("showpublish",false);
        model.addAttribute("username",username);
        model.addAttribute("password",password);
        if (username.trim().equals("")||password.trim().equals("")){
            model.addAttribute("error","账号或者密码不能为空！");
            return "login";
        }
        if (username.trim().length()>5){
            model.addAttribute("error","这是测试！");
            return "login";
        }
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
        else {
            model.addAttribute("error","账号或者密码错误！");
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/GetVerificationCode")
    public String GetVerificationCode(@RequestParam(value = "NickName") String NickName,
                                      @RequestParam(value = "age") String age,
                                      @RequestParam(value = "inlineRadioOptions") String inlineRadioOptions,
                                      @RequestParam(value = "phone") String phone,
                                      @RequestParam(value = "VerificationCode") String VerificationCode,
                                      @RequestParam(value = "password") String password,
                                      @RequestParam(value = "adress") String adress,Model model){
        model.addAttribute("showlogin",true);
        model.addAttribute("showpublish",false);
        model.addAttribute("NickName",NickName);
        model.addAttribute("age",age);
        model.addAttribute("inlineRadioOptions",inlineRadioOptions);
        model.addAttribute("phone",phone);
        model.addAttribute("VerificationCode",VerificationCode);
        model.addAttribute("password",password);
        model.addAttribute("adress",adress);


        return "register";
    }
}
