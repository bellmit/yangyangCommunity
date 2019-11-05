package cn.yangyang.community.service;

import cn.yangyang.community.dto.PageDto;
import cn.yangyang.community.dto.PublishDto;
import cn.yangyang.community.mapper.PublishMapper;
import cn.yangyang.community.mapper.UserMapper;
import cn.yangyang.community.pojo.Publish;
import cn.yangyang.community.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublishService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PublishMapper publishMapper;

    public PageDto list(Integer page, Integer pagesize){
        //返回值
        PageDto pageDto=new PageDto();
        //根据页码数查数据
        Integer offsize=pagesize*(page-1);
        List<Publish> publishList = publishMapper.Select(offsize,pagesize);
        //用来存放publish数据和user数据
        List<PublishDto> publishDtoList=new ArrayList<>();
        for (Publish publish : publishList) {
            User user = userMapper.FindByUser(publish.getUser_ID());
            PublishDto dto=new PublishDto();
            //快速赋值
            BeanUtils.copyProperties(publish,dto);
            dto.setUser(user);
            publishDtoList.add(dto);
        }
        pageDto.setPublishData(publishDtoList);
        pageDto.Method(publishMapper.Count(),page,pagesize);
        return pageDto;
    }
}
