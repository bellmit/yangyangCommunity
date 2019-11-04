package cn.yangyang.community.controller;

import cn.yangyang.community.mapper.PublishMapper;
import cn.yangyang.community.pojo.Publish;
import cn.yangyang.community.pojo.User;
import com.alibaba.fastjson.JSON;
import com.mysql.cj.xdevapi.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PublishController {

    @Autowired
    private PublishMapper publishMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/SubmitPublish")
    public String SubmitPublish(@RequestParam(value = "title") String title,
                                @RequestParam(value = "desc") String desc,
                                @RequestParam(value = "tag") String tag,
                                HttpServletRequest request, Model model){
        model.addAttribute("title",title);
        model.addAttribute("desc",desc);
        model.addAttribute("tag",tag);
        if (null==title||"".equals(title)){
            model.addAttribute("error","标题不能为空！");
            return "publish";
        }
        else if (null==desc||"".equals(desc)){
            model.addAttribute("error","描述不能为空！");
            return "publish";
        }
        else if (null==tag||"".equals(tag)){
            model.addAttribute("error","标签不能为空！");
            return "publish";
        }
        try {
            Publish publish=new Publish();
            publish.setPublish_Title(title);
            publish.setPublish_Desc(desc);
            publish.setTag(tag);
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            publish.setPublish_Datetime(dateFormat.format(date));
            User user = (User) request.getSession().getAttribute("user");
            publish.setUser_ID(user.getUser_ID());
            publishMapper.insert(publish);
        }
        catch (Exception e){
            model.addAttribute("error","发布失败！");
            e.printStackTrace();
            return "publish";
        }

        return "redirect:/";
    }
}
